/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.views;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.teiid.designer.ui.common.eventsupport.SelectionProvider;
import org.teiid.designer.ui.properties.ModelObjectPropertySourceProvider;
import org.teiid.designer.ui.undo.IUndoManager;
import org.teiid.designer.ui.viewsupport.ModelUtilities;
import org.teiid.designer.ui.viewsupport.StatusBarUpdater;


/**
 * ModelerView is a base class for adding property and status bar support to ViewParts in the Modeler.
 *
 * @since 8.0
 */
public abstract class ModelerView extends ViewPart {

    protected ModelObjectPropertySourceProvider propertySourceProvider;
    protected ISelectionProvider selectionProvider = new SelectionProvider();
    protected ISelectionListener selectionListener;
    protected StatusBarUpdater statusBarListener;

    /**
     * Construct an instance of ModelerView.
     * 
     */
    public ModelerView() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IViewPart#init(org.eclipse.ui.IViewSite)
     */
    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);

        site.setSelectionProvider(selectionProvider);

        this.selectionListener = new ISelectionListener() {
            @Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                if (part != ModelerView.this && !(part instanceof PropertySheet)) {
                    selectionProvider.setSelection(selection);
                }
            }
        };
        site.getWorkbenchWindow().getSelectionService().addSelectionListener(this.selectionListener);
    }

    protected ISelectionChangedListener getStatusBarListener() {
        return statusBarListener;
    }

    /**
     * Overridden from super to provide the PropertySheet for EObject, since EObject
     * does not implement IAdaptable
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     * @since 4.0
     */
    @Override
    public Object getAdapter(Class key) {

        if (key.equals(IPropertySheetPage.class)) {
            if (propertySourceProvider == null) {
                propertySourceProvider = ModelUtilities.getPropertySourceProvider(); // new ModelObjectPropertySourceProvider();
            }

            return propertySourceProvider.getPropertySheetPage();
        }

        if (key.equals(IUndoManager.class)) {
            return getUndoManager();
        }

        return super.getAdapter(key);
    }

    protected IUndoManager getUndoManager() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        // hook up our status bar manager for EObjects
        IStatusLineManager slManager = getViewSite().getActionBars().getStatusLineManager();
        statusBarListener = new StatusBarUpdater(slManager);
        selectionProvider.addSelectionChangedListener(statusBarListener);

        // set the initial selection to the current workbench selection
        ISelection selection = getSite().getWorkbenchWindow().getSelectionService().getSelection();
        if (selection != null) {
            selectionProvider.setSelection(selection);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this.selectionListener);
        super.dispose();
    }

}

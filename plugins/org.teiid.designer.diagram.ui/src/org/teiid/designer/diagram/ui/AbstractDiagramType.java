/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui;


import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.diagram.ui.editor.DiagramController;
import org.teiid.designer.diagram.ui.editor.DiagramEditor;
import org.teiid.designer.diagram.ui.editor.DiagramSelectionHandler;
import org.teiid.designer.diagram.ui.editor.DiagramViewer;
import org.teiid.designer.diagram.ui.editor.IDiagramSelectionHandler;
import org.teiid.designer.diagram.ui.model.DiagramModelNode;
import org.teiid.designer.diagram.ui.notation.uml.model.DefaultClassContentAdapter;
import org.teiid.designer.diagram.ui.notation.uml.model.IClassifierContentAdapter;
import org.teiid.designer.diagram.ui.pakkage.IPackageDiagramProvider;
import org.teiid.designer.diagram.ui.util.DiagramUiUtilities;
import org.teiid.designer.diagram.ui.util.colors.ColorPaletteManager;
import org.teiid.designer.diagram.ui.util.colors.DefaultColorPaletteManager;
import org.teiid.designer.metamodels.diagram.Diagram;
import org.teiid.designer.ui.viewsupport.ModelObjectUtilities;


/**
 * @author PForhan
 *
 * @since 8.0
 */
public abstract class AbstractDiagramType implements IDiagramType {

    //
    // Class variables:
    //
    private ColorPaletteManager colorPaletteManager;

    //
    // Instance variables:
    //
    private String typeId;

    //
    // Implementation of IDiagramType methods:
    //
    @Override
	public boolean dependsOnResource(DiagramModelNode root, IResource res) {
        // Query all the objects in the diagram to see from whence they come.
        if( root != null ) {
            boolean result = false;
            //start txn
            boolean requiredStart = ModelerCore.startTxn(false, false, "Check Resource Dependency", root); //$NON-NLS-1$
            boolean succeeded = false;
            try {
                result = ModelObjectUtilities.didResourceContainAny(res, DiagramUiUtilities.getEObjects(root));
                succeeded = true;
            } finally {
                //if we started the txn, commit it.
                if(requiredStart){
                    if(succeeded) {
                        ModelerCore.commitTxn();
                    } else {
                        ModelerCore.rollbackTxn();
                    }
                }
            }
            return result;
        }
        return false;
    }

    @Override
	public IClassifierContentAdapter getClassifierContentAdapter() {
        return new DefaultClassContentAdapter();
    }

    @Override
	public ColorPaletteManager getColorPaletteManager() {
        if( colorPaletteManager == null )
            colorPaletteManager = new DefaultColorPaletteManager();
            
        return colorPaletteManager;
    }

    @Override
	public DiagramController getDiagramController(DiagramEditor editor) {
        return null;
    }
    
    @Override
    public Class<DiagramController> getDiagramControllerClass() {
        return null;
    }

    @Override
	public Diagram getDiagramForGoToMarkerEObject(EObject eObject) {
        return null;
    }

    @Override
	public Object getDiagramSelectionStandin(Diagram diagram) {
        return diagram;
    }

    @Override
	public EObject getInitialSelection(Object object) {
        return null;
    }

    @Override
	public IPackageDiagramProvider getPackageDiagramProvider() {
        return null;
    }

    @Override
	public EObject getRevealedEObject(Diagram diagram, Object object) {
        return null;
    }

    @Override
	public String getType() {
        return typeId;
    }

    @Override
	public boolean isDiagramLarge(Diagram diagram) {
    	return false;
    }

    @Override
	public boolean isDiagramTooLarge(Diagram diagram) {
    	return false;
    }

    @Override
	public boolean isTransientDiagram(Diagram diagram) {
        return false;
    }

    @Override
	public void setDisplayName(String displayName) {
        // does nothing. Name set by plugin constants.
    }

    @Override
	public void setType(String diagramType) {
        typeId = diagramType;
    }

    @Override
	public IDiagramSelectionHandler getSelectionHandler(DiagramViewer viewer) {
        return new DiagramSelectionHandler(viewer);
    }
    
    public void setColorPaletteManager(ColorPaletteManager colorPaletteManager) {
        this.colorPaletteManager = colorPaletteManager;
    }
}

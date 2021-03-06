/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * @since 8.0
 */
public class TextComponentSet extends SimpleComponentSet {

    private final int mystyle;
    Text text;
    private String lastSetValue;
    private MyModifyListener modList;
    final DialogProvider provider;

    public TextComponentSet( String id,
                             String labelName,
                             int style ) {
        this(id, labelName, style, null);
    }

    public TextComponentSet( String id,
                             String labelName,
                             int style,
                             DialogProvider provider ) {
        super(id, labelName);
        mystyle = style;
        this.provider = provider;
    }

    @Override
    protected void addControls( Composite parent,
                                FormToolkit ftk ) {
        // init:
        init();

        // Set up controls:
        text = ftk.createText(parent, null, mystyle);
        TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
        text.setLayoutData(twd);

        if ((mystyle & SWT.MULTI) != 0) {
            // was multi, make height taller:
            twd.heightHint = 50;
        } // endif

        text.addModifyListener(modList);

        if (provider != null) {
            // add a popup button:
            Button b = ftk.createButton(parent, provider.getLaunchButtonText(), SWT.NONE);
            b.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    provider.showDialog(text.getShell(), getValue());
                    if (!provider.wasCancelled()) {
                        setValue(provider.getValue());
                    } // endif
                }
            });
            twd = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
            b.setLayoutData(twd);
        } // endif
    }

    private void init() {
        if (modList == null) {
            modList = new MyModifyListener();
        } // endif
    }

    @Override
    public int getControlCount() {
        if (provider != null) {
            return 3;
        } // endif

        return 2;
    }

    @Override
    public void setEditible( boolean enabled ) {
        super.setEditible(enabled);
        if (text != null) {
            text.setEnabled(true); // to allow scrolling
            text.setEditable(enabled);
        } // endif
    }

    @Override
	public void reset() {
        setValue(lastSetValue);
    }

    @Override
	public boolean isUserSet() {
        // TODO this needs to be able to deal with nulls and ""s
        return !(getValue().equals(lastSetValue));
    }

    @Override
    protected void addMonitor( ComponentSetMonitor monitor ) {
        init();
        modList.mon = monitor;
    }

    @Override
    protected void removeMonitor( ComponentSetMonitor monitor ) {
        // TODO should I remove the listener here? I could re-add it above...
        init();
        modList.mon = null;
    }

    @Override
	public void setValue( Object o ) {
        if (o instanceof String) {
            lastSetValue = (String)o;
        } else {
            lastSetValue = ""; //$NON-NLS-1$
        } // endif
        text.setText(lastSetValue);
    }

    public String getValue() {
        return text.getText();
    }

    class MyModifyListener implements ModifyListener {
        public ComponentSetMonitor mon;
        private String lastVal;

        @Override
		public void modifyText( ModifyEvent e ) {
            if (mon != null) {
                String newVal = getValue();
                if (!FormUtil.safeEquals(newVal, lastVal)) {
                    mon.update(new ComponentSetEvent(TextComponentSet.this, false, newVal));
                    lastVal = newVal;
                } // endif
            } // endif
        }
    }
}

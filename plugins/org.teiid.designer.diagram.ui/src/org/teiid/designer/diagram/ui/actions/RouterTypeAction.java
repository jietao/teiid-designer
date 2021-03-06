/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.actions;

/** 
 * @since 8.0
 */
public class RouterTypeAction extends DiagramAction {
    private String type = null;
    private int index = -1;
    private RouterTypeMenuManager manager;
    /** 
     * @param thePlugin
     * @since 4.2
     */
    public RouterTypeAction(String type, int index, RouterTypeMenuManager manager) {
        super();
        this.type = type;
        setText(type);
        this.manager = manager;
        this.index = index;
    }


    /** 
     * @see org.teiid.designer.ui.common.actions.AbstractAction#doRun()
     * @since 4.2
     */
    @Override
    protected void doRun() {
        manager.handleSelection(index, false);
    }

    public String getType() {
        return this.type;
    }
}

/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.transformation.ui.part;

import java.util.List;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.ModelerCore;
import org.teiid.designer.diagram.ui.model.DiagramModelNode;
import org.teiid.designer.diagram.ui.notation.uml.part.UmlClassifierEditPart;
import org.teiid.designer.diagram.ui.part.DiagramEditPart;
import org.teiid.designer.diagram.ui.part.DropEditPartHelper;
import org.teiid.designer.diagram.ui.util.DiagramUiUtilities;
import org.teiid.designer.metamodels.diagram.Diagram;
import org.teiid.designer.transformation.ui.actions.ITransformationDiagramActionConstants;
import org.teiid.designer.transformation.ui.actions.TransformationSourceManager;
import org.teiid.designer.transformation.util.TransformationHelper;
import org.teiid.designer.ui.viewsupport.ModelObjectUtilities;



/** 
 * @since 8.0
 */
public class TransformationDropEditPartHelper extends DropEditPartHelper {
    private Object transformation;
    
    /** 
     * 
     * @since 4.3
     */
    public TransformationDropEditPartHelper(Object transformation) {
        super();
        this.transformation = transformation;
    }

    /**
     * Implemented to determine which role container to drop the incoming eObject list. 
     * @see org.teiid.designer.diagram.ui.part.DropEditPart#drop(org.eclipse.draw2d.geometry.Point, java.util.List)
     * @since 4.2
     */
    @Override
    public void drop(Point dropPoint, List dropList) {
        // We need to see if we can add the list of objects to the transformation
        boolean canAdd = false;

        if( transformation != null )
            canAdd = TransformationSourceManager.canAdd((EObject)transformation, dropList, this);
        
        if( canAdd ) {
            boolean canUndo = ITransformationDiagramActionConstants.DiagramActions.UNDO_ADD_TRANSFORMATION_SOURCE;
            //start txn
            boolean requiredStart = ModelerCore.startTxn(true, canUndo, "Add Sources", null); //$NON-NLS-1$
            boolean succeeded = false;
            try {
                // Add each source
                TransformationSourceManager.addSources(transformation, dropList);
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
            
            
        } else {
            // Need to throw up a dialog stating that user can't add these objects to the 
            TransformationSourceManager.warnUserAboutInvalidSources(dropList);
        }
    }

    /** 
     * @see org.teiid.designer.diagram.ui.part.DropEditPart#allowsDrop(org.eclipse.draw2d.geometry.Point, java.util.List)
     * @since 4.3
     */
    @Override
	public boolean allowsDrop(Object target,
                              List dropList) {
        if( TransformationSourceManager.canAdd((EObject)transformation, dropList, this) &&
            target instanceof DiagramEditPart ) {
            // Targets can only be 
            // 1) SqlTransformationMappingRoot (same as transformation)
            // 2) VirtualTable with t-root same as transformation
            // 3) T-Diagram, with t-Root same as transformation
            EObject transform = getTransformation((DiagramEditPart)target);
            if( transform != null && transformation != null) {
                if( transform.equals(transformation))
                    return true;
            }
        }
        return false;
    }
    
    protected EObject getTransformation(DiagramEditPart editPart) {
        if( editPart instanceof TransformationDiagramEditPart ) {
            return TransformationSourceManager.getTransformationFromDiagram((Diagram)editPart.getModelObject());
        } else if( editPart instanceof TransformationEditPart ) {
            return editPart.getModelObject();
        } else if( editPart instanceof UmlClassifierEditPart ) {
            if( !ModelObjectUtilities.isVirtual(editPart.getModelObject())) {
                return null;
            }
            // check to see if it's the "Target"
            DiagramModelNode parentClassifierNode = DiagramUiUtilities.getClassifierParentNode((DiagramModelNode)editPart.getModel());
            if( parentClassifierNode != null ) {
                if(TransformationHelper.isValidSqlTransformationTarget(parentClassifierNode.getModelObject())) {
                    return TransformationHelper.getTransformationMappingRoot(parentClassifierNode.getModelObject());
                }
                // Now we check for is Virtual?
            }
        }
        
        return null;
    }
}

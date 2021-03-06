/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.model;

/**
 * @since 8.0
 */
public interface ExpandableNode {
    
    boolean isExpanded();

    void expand();

    void collapse();
}

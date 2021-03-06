/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.common.table;

import java.util.Comparator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * @param <T>
 * @param <V>
 *
 * @since 8.0
 */
public interface ColumnProvider<T, V> extends Comparator<T> {

    /**
     * @return {@link SWT#LEFT}, {@link SWT#CENTER}, or {@link SWT#RIGHT}
     */
    int getAlignment();

    /**
     * @return the cell editor that will be used to edit the {@link Column column's} {@link #isEditable(Object) editable} cells
     */
    Class<? extends CellEditor> getEditorClass();

    /**
     * @param element
     * @return the {@link Column column's} image for the supplied element value
     */
    Image getImage( T element );

    /**
     * @return the header name of the {@link Column column}
     */
    String getName();
    
    /**
     * @return the header image of the {@link Column column}
     */
    Image getImage();

    /**
     * @param element
     * @return the {@link Column column's} text for the supplied element value (often just the string representation of the
     *         cell's {@link #getValue(Object) value})
     */
    String getText( T element );

    /**
     * @param element
     * @return the {@link Column column's} tooltip for the supplied element value
     */
    String getToolTip( T element );

    /**
     * @param element
     * @return the {@link Column column's} model value for the supplied element value; must not be <code>null</code>
     */
    V getValue( T element );

    /**
     * @param element
     * @return <code>true</code> if the {@link Column column} is editable for the supplied element value
     */
    boolean isEditable( T element );

    /**
     * @return <code>true</code> if the column is resizable
     */
    boolean isResizable();

    /**
     * @return <code>true</code> if the column is sortable
     */
    boolean isSortable();

    /**
     * Must be implemented if the {@link Column column} is {@link #isEditable(Object) editable} for the supplied element value.
     * 
     * @param element
     * @param value
     */
    void setValue( T element,
                   V value );
}

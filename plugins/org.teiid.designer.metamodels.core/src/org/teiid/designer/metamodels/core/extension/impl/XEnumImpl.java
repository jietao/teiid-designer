/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.core.extension.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.teiid.designer.metamodels.core.extension.ExtensionPackage;
import org.teiid.designer.metamodels.core.extension.XEnum;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>XEnum</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 *
 * @since 8.0
 */
public class XEnumImpl extends EEnumImpl implements XEnum {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected XEnumImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ExtensionPackage.Literals.XENUM;
    }

} // XEnumImpl

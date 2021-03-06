/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.core.extension.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl;
import org.teiid.designer.metamodels.core.extension.ExtensionPackage;
import org.teiid.designer.metamodels.core.extension.XEnumLiteral;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>XEnum Literal</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 *
 * @since 8.0
 */
public class XEnumLiteralImpl extends EEnumLiteralImpl implements XEnumLiteral {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected XEnumLiteralImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ExtensionPackage.Literals.XENUM_LITERAL;
    }

} // XEnumLiteralImpl

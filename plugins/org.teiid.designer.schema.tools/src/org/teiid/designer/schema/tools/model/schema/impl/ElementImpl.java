/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.schema.tools.model.schema.impl;

import java.util.Iterator;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.teiid.designer.schema.tools.model.schema.Column;
import org.teiid.designer.schema.tools.model.schema.ISchemaModelCopyTraversalContext;
import org.teiid.designer.schema.tools.model.schema.Relationship;
import org.teiid.designer.schema.tools.model.schema.RootElement;
import org.teiid.designer.schema.tools.model.schema.SchemaObject;
import org.teiid.designer.schema.tools.model.schema.SchemaObjectKey;

/**
 * @since 8.0
 */
public class ElementImpl extends BaseSchemaObject {

    protected XSDElementDeclaration elem;

    public XSDElementDeclaration getElem() {
		return elem;
	}

	public ElementImpl( XSDElementDeclaration elem,
                        String namespacePrefix,
                        XSDTypeDefinition type,
                        XSDSchema schema ) {
        super(namespacePrefix, type, schema);
        this.elem = elem;
        this.doesNotHaveUniqueName = false;
    }

    @Override
	public SchemaObject copy( ISchemaModelCopyTraversalContext ctx ) {
        ElementImpl copy = new ElementImpl(elem, getNamespacePrefix(), type, schema);
        return copy;
    }

    @Override
    public boolean equals( Object obj ) {
        if (obj instanceof ElementImpl) {
            ElementImpl test = (ElementImpl)obj;
            if (elem == test.elem) {
                return true;
            }
        }
        return false;
    }

    @Override
	public String getSimpleName() {
        String elemName = elem.getName();
        String uniqueName;
        if (doesNotHaveUniqueName) {
            String typeName = elem.getType().getName();
            uniqueName = elemName + '(' + typeName + ')';
        } else {
            uniqueName = elemName;
        }
        return uniqueName;
    }

    @Override
	public XSDTypeDefinition getType() {
        return type;
    }

    @Override
	public String getElementTypeNamespace() {
        String namespace = elem.getType().getTargetNamespace();
        return namespace;
    }

    @Override
	public String getNamespace() {
        return elem.getTargetNamespace();
    }

    @Override
	public String getName() {
        return elem.getName();
    }

    @Override
    public String toString() {
        String retval = elem.getName();
        if (elem.getTargetNamespace() != null) {
            retval += " (" + elem.getTargetNamespace() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return retval;
    }

    @Override
	public RootElement getRootRepresentation() {
        return new RootElementImpl(getKey(), getSimpleName(), getNamespace(), isCanBeRoot());
    }

    @Override
	public String getCatalog() {
        return null;
    }

    @Override
	public int getMinOccurs() {
        XSDTypeDefinition def = elem.getTypeDefinition();
        XSDParticle particle = def.getComplexType();
        if (particle == null) return 1;
        return particle.getMinOccurs();
    }

    @Override
	public int getMaxOccurs() {
        XSDTypeDefinition def = elem.getTypeDefinition();
        XSDParticle particle = def.getComplexType();
        if (particle == null) return 1;
        return particle.getMaxOccurs();
    }

    public void printDebug() {
        StringBuffer buff = new StringBuffer();
        buff.append("\t SchemaObject: SimpleName = " + getSimpleName()); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("Prefix = " + getNamespacePrefix()); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("AvailableRoot = " + availableRoot); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("doesNotHaveUniqueName = " + doesNotHaveUniqueName); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("filename = " + fileName); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("representAsTable = " + representAsTable); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        buff.append("withinSelectedHierarchy = " + withinSelectedHierarchy); //$NON-NLS-1$
        buff.append(" "); //$NON-NLS-1$
        System.out.println(buff.toString());
        if (attributes.size() > 0) System.out.println("\t Attributes"); //$NON-NLS-1$
        for (Iterator iter = attributes.iterator(); iter.hasNext();) {
            Column column = (Column)iter.next();
            column.printDebug();
        }
        if (children.size() > 0) System.out.println("\t Children"); //$NON-NLS-1$
        for (Iterator iter = children.iterator(); iter.hasNext();) {
            Relationship relationship = (Relationship)iter.next();
            relationship.printDebug();
        }
        if (parents.size() > 0) System.out.println("\t Parents"); //$NON-NLS-1$
        for (Iterator iter = parents.iterator(); iter.hasNext();) {
            Relationship relationship = (Relationship)iter.next();
            relationship.printDebug();
        }

    }

    @Override
	public SchemaObjectKey getKey() {
        return new ElementImplKey(elem);
    }
}

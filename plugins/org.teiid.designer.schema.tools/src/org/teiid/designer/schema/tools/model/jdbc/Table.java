/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.schema.tools.model.jdbc;


import org.jdom.Namespace;
import org.teiid.designer.schema.tools.model.schema.SchemaModel;
import org.teiid.designer.schema.tools.model.schema.SchemaObject;

/**
 * @since 8.0
 */
public interface Table extends DatabaseElement {

	/**
	 * Returns a String of all the namespace declarations in prefix:uri form as
	 * a single String with spaces between each declaration
	 * 
	 * @return the String of all the Namespace declarations
	 */
	public String getNamespaceDeclaration();

	/**
	 * 
	 * Add a namespace to the list of namespaces to declare
	 * 
	 * @param ns - the namespace to add
	 */
	public void addNamespace(Namespace ns);

	public String getCatalog();

	public void setCatalog(String catalog);
	
	
	public String getSchema();
	
	public void setSchema(String schema);

	/**
	 * 
	 * Add a column to the table
	 * 
	 * @param column
	 *            the column to add
	 */
	public void addColumn(Column column);

	/**
	 * 
	 * Returns all of the columns contained in the table
	 * 
	 * @return an array of Columns
	 */
	public Column[] getColumns();
	
	/**
	 * 
	 * Returns all of the tables that have a foreign key relationship
	 * to this table.
	 * 
	 * @return an array of Tables
	 */
	public Table[] getChildTables();
	
	/**
	 * 
	 * Returns all of the tables that this table has a foreign key relationship to.
	 * 
	 * @return an array of Tables
	 */
	public Table[] getParentTables();

	/**
	 * Returns the Relationship value that defines the tables relation to its parent.
	 * @return - the Relationship value.
	 */
	public int getRelationToParent();

	public int getMaxOccurs();

	public void setSchemaModel(SchemaModel schemaModel);

	public void setBase(boolean b);

	public SchemaObject getElement();

	public void setElement(SchemaObject element);
}

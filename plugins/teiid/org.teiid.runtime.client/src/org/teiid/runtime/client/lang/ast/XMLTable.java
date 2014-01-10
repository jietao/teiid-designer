/* Generated By:JJTree: Do not edit this line. XMLTable.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import java.util.ArrayList;
import java.util.List;
import org.teiid.runtime.client.lang.TeiidNodeFactory.ASTNodes;
import org.teiid.runtime.client.lang.parser.TeiidParser;
import org.teiid.runtime.client.types.DataTypeManagerService;

public class XMLTable extends FromClause {

    private GroupSymbol symbol;

    private List<XMLColumn> columns = new ArrayList<XMLColumn>();

    private String xquery;

    private List<DerivedColumn> passing = new ArrayList<DerivedColumn>();

    private boolean usingDefaultColumn;

    private XMLNamespaces namespaces;

    public XMLTable(int id) {
        super(id);
    }

    public XMLTable(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Get GroupSymbol representing the named subquery 
     * @return GroupSymbol representing the subquery
     */
    public GroupSymbol getGroupSymbol() {
        return this.symbol;    
    }
    
    /** 
     * Reset the alias for this subquery from clause and it's pseudo-GroupSymbol.  
     * WARNING: this will modify the hashCode and equals semantics and will cause this object
     * to be lost if currently in a HashMap or HashSet.
     * @param name New name
     * @since 4.3
     */
    public void setName(String name) {
        this.symbol = this.parser.createASTNode(ASTNodes.GROUP_SYMBOL);
        this.symbol.setName(name);
    }

    public List<DerivedColumn> getPassing() {
        return passing;
    }

    public void setPassing(List<DerivedColumn> passing) {
        this.passing = passing;
    }

    public String getXquery() {
        return xquery;
    }
    
    public void setXquery(String xquery) {
        this.xquery = xquery;
    }

    public boolean isUsingDefaultColumn() {
        return usingDefaultColumn;
    }

    /**
     * @param usingDefaultColumn the usingDefaultColumn to set
     */
    public void setUsingDefaultColumn(boolean usingDefaultColumn) {
        this.usingDefaultColumn = usingDefaultColumn;
    }

    public List<XMLColumn> getColumns() {
        return columns;
    }
    
    public void setColumns(List<XMLColumn> columns) {
        if (columns.isEmpty()) {
            usingDefaultColumn = true;
            XMLColumn xmlColumn = parser.createASTNode(ASTNodes.XML_COLUMN);
            xmlColumn.setName("OBJECT_VALUE"); //$NON-NLS-1$
            xmlColumn.setType(DataTypeManagerService.DefaultDataTypes.XML.getId());
            xmlColumn.setPath("."); //$NON-NLS-1$
            columns.add(xmlColumn);
        }
        this.columns = columns;
    }

    public XMLNamespaces getNamespaces() {
        return namespaces;
    }
    
    public void setNamespaces(XMLNamespaces namespaces) {
        this.namespaces = namespaces;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.columns == null) ? 0 : this.columns.hashCode());
        result = prime * result + ((this.namespaces == null) ? 0 : this.namespaces.hashCode());
        result = prime * result + ((this.passing == null) ? 0 : this.passing.hashCode());
        result = prime * result + ((this.symbol == null) ? 0 : this.symbol.hashCode());
        result = prime * result + (this.usingDefaultColumn ? 1231 : 1237);
        result = prime * result + ((this.xquery == null) ? 0 : this.xquery.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        XMLTable other = (XMLTable)obj;
        if (this.columns == null) {
            if (other.columns != null) return false;
        } else if (!this.columns.equals(other.columns)) return false;
        if (this.namespaces == null) {
            if (other.namespaces != null) return false;
        } else if (!this.namespaces.equals(other.namespaces)) return false;
        if (this.passing == null) {
            if (other.passing != null) return false;
        } else if (!this.passing.equals(other.passing)) return false;
        if (this.symbol == null) {
            if (other.symbol != null) return false;
        } else if (!this.symbol.equals(other.symbol)) return false;
        if (this.usingDefaultColumn != other.usingDefaultColumn) return false;
        if (this.xquery == null) {
            if (other.xquery != null) return false;
        } else if (!this.xquery.equals(other.xquery)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=7e5530ab340020c0908e2b5c8b4448f3 (do not edit this line) */

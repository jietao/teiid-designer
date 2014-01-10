/* Generated By:JJTree: Do not edit this line. XMLNamespaces.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import java.util.List;
import org.teiid.runtime.client.lang.parser.TeiidParser;

public class XMLNamespaces extends SimpleNode {

    private List<NamespaceItem> namespaceItems;

    public XMLNamespaces(int id) {
        super(id);
    }

    public XMLNamespaces(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * @return the namespaceItems
     */
    public List<NamespaceItem> getNamespaces() {
        return this.namespaceItems;
    }

    /**
     * @param namespaceItems the namespaceItems to set
     */
    public void setNamespaces(List<NamespaceItem> namespaceItems) {
        this.namespaceItems = namespaceItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.namespaceItems == null) ? 0 : this.namespaceItems.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        XMLNamespaces other = (XMLNamespaces)obj;
        if (this.namespaceItems == null) {
            if (other.namespaceItems != null) return false;
        } else if (!this.namespaceItems.equals(other.namespaceItems)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=736a2961c19def96febeb5ccbed0cdcf (do not edit this line) */

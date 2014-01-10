/* Generated By:JJTree: Do not edit this line. SetClauseList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import java.util.ArrayList;
import java.util.List;
import org.teiid.runtime.client.lang.parser.TeiidParser;

public class SetClauseList extends SimpleNode {

    private List<SetClause> setClauses = new ArrayList<SetClause>();

    public SetClauseList(int id) {
        super(id);
    }

    public SetClauseList(TeiidParser p, int id) {
        super(p, id);
    }
    
    public void addClause(SetClause clause) {
        this.setClauses.add(clause);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.setClauses == null) ? 0 : this.setClauses.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        SetClauseList other = (SetClauseList)obj;
        if (this.setClauses == null) {
            if (other.setClauses != null) return false;
        } else if (!this.setClauses.equals(other.setClauses)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=03e0d8ce7120831af6c8c5e5b956b453 (do not edit this line) */

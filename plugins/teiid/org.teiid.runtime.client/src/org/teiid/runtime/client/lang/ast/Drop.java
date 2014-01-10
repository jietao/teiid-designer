/* Generated By:JJTree: Do not edit this line. Drop.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.parser.TeiidParser;

public class Drop extends Command {

    /** Identifies the table to be dropped. */
    private GroupSymbol table;

    public Drop(int id) {
        super(id);
    }

    public Drop(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * @return the table
     */
    public GroupSymbol getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(GroupSymbol table) {
        this.table = table;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.table == null) ? 0 : this.table.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Drop other = (Drop)obj;
        if (this.table == null) {
            if (other.table != null) return false;
        } else if (!this.table.equals(other.table)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=cf112d019f8dc39785f359562092f510 (do not edit this line) */

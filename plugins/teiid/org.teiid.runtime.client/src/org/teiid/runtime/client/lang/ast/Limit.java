/* Generated By:JJTree: Do not edit this line. Limit.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.parser.TeiidParser;

public class Limit extends SimpleNode {

    public static String NON_STRICT = "NON_STRICT"; //$NON-NLS-1$

    private Expression offset;

    private Expression rowLimit;

    private boolean implicit;

    private boolean strict = true;

    public Limit(int id) {
        super(id);
    }

    public Limit(TeiidParser p, int id) {
        super(p, id);
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }
    
    public boolean isStrict() {
        return strict;
    }
    
    public boolean isImplicit() {
        return implicit;
    }
    
    public void setImplicit(boolean implicit) {
        this.implicit = implicit;
    }
    
    public Expression getOffset() {
        return offset;
    }
    
    public void setOffset(Expression offset) {
        this.offset = offset;
    }
    
    public Expression getRowLimit() {
        return rowLimit;
    }
    
    public void setRowLimit(Expression rowLimit ) {
        this.rowLimit = rowLimit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.implicit ? 1231 : 1237);
        result = prime * result + ((this.offset == null) ? 0 : this.offset.hashCode());
        result = prime * result + ((this.rowLimit == null) ? 0 : this.rowLimit.hashCode());
        result = prime * result + (this.strict ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Limit other = (Limit)obj;
        if (this.implicit != other.implicit) return false;
        if (this.offset == null) {
            if (other.offset != null) return false;
        } else if (!this.offset.equals(other.offset)) return false;
        if (this.rowLimit == null) {
            if (other.rowLimit != null) return false;
        } else if (!this.rowLimit.equals(other.rowLimit)) return false;
        if (this.strict != other.strict) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=ac356bcb126e51b23a771bf5e2b89dfc (do not edit this line) */

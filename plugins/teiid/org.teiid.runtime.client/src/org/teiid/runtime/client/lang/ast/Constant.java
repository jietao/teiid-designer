/* Generated By:JJTree: Do not edit this line. Constant.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.parser.TeiidParser;

public class Constant extends SimpleNode implements Expression {

    private Class<?> type;

    public Constant(int id) {
        super(id);
    }

    public Constant(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Get type of constant, if known
     * @return Java class name of type
     */
    @Override
    public Class<?> getType() {
        return this.type;
    }
    
    /**
     * TODO: remove me when a null type is supported
     * @param type
     */
    public void setType(Class<?> type) {
        this.type = type;
    }

    /**
     * Get value of constant
     * @return Constant value
     */
    public Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Constant other = (Constant)obj;
        if (this.type == null) {
            if (other.type != null) return false;
        } else if (!this.type.equals(other.type)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=6271d54b51de261eb4775571a208cc1b (do not edit this line) */

/* Generated By:JJTree: Do not edit this line. QueryString.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import java.util.List;
import org.teiid.runtime.client.lang.parser.TeiidParser;
import org.teiid.runtime.client.types.DataTypeManagerService.DefaultDataTypes;

public class QueryString extends SimpleNode implements Expression {

    private List<DerivedColumn> args;

    private Expression path;

    public QueryString(int id) {
        super(id);
    }

    public QueryString(TeiidParser p, int id) {
        super(p, id);
    }

    @Override
    public Class<?> getType() {
        return DefaultDataTypes.STRING.getTypeClass();
    }

    /**
     * @return the args
     */
    public List<DerivedColumn> getArgs() {
        return this.args;
    }

    /**
     * @param args the args to set
     */
    public void setArgs(List<DerivedColumn> args) {
        this.args = args;
    }

    /**
     * @return the path
     */
    public Expression getPath() {
        return this.path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(Expression path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.path == null) ? 0 : this.path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        QueryString other = (QueryString)obj;
        if (this.args == null) {
            if (other.args != null) return false;
        } else if (!this.args.equals(other.args)) return false;
        if (this.path == null) {
            if (other.path != null) return false;
        } else if (!this.path.equals(other.path)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=442d58224d7e811bdda7829b570b4b56 (do not edit this line) */

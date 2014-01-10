/* Generated By:JJTree: Do not edit this line. MatchCriteria.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.parser.TeiidParser;
import org.teiid.runtime.client.util.PropertiesUtil;

public class MatchCriteria extends Criteria {

    /** The internal null escape character */
    private static final char NULL_ESCAPE_CHAR = 0;

    private static final char DEFAULT_ESCAPE_CHAR = PropertiesUtil.getBooleanProperty(System.getProperties(), "org.teiid.backslashDefaultMatchEscape", false)?'\\':NULL_ESCAPE_CHAR; //$NON-NLS-1$

    public enum MatchMode {
        /**
         * Like Mode
         */
        LIKE,

        /**
         * Similar Mode
         */
        SIMILAR,

        /**
         * The escape char is typically not used in regex mode.
         */
        REGEX
    }

    /** The left-hand expression. */
    private Expression leftExpression;
    
    /** The right-hand expression. */
    private Expression rightExpression;

    private boolean negated;

    /** The escape character or '' if there is none */
    private char escapeChar = DEFAULT_ESCAPE_CHAR;

    private MatchMode mode = MatchMode.LIKE;

    public MatchCriteria(int id) {
        super(id);
    }

    public MatchCriteria(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Get left expression.
     * @return Left expression
     */
    public Expression getLeftExpression() {
        return this.leftExpression;
    }

    /**
     * Set left expression.
     * @param expression expression
     */
    public void setLeftExpression(Expression expression) { 
        this.leftExpression = expression;
    }

    /**
     * Get right expression.
     * @return right expression
     */
    public Expression getRightExpression() {
        return this.rightExpression;
    }

    /**
     * Set right expression.
     * @param expression expression
     */
    public void setRightExpression(Expression expression) { 
        this.rightExpression = expression;
    }

    /**
     * Returns whether this criteria is negated.
     * @return flag indicating whether this criteria contains a NOT
     */
    public boolean isNegated() {
        return negated;
    }
    
    /**
     * Sets the negation flag for this criteria.
     * @param negationFlag true if this criteria contains a NOT; false otherwise
     */
    public void setNegated(boolean negationFlag) {
        negated = negationFlag;
    }

    /**
     * Get the escape character, which can be placed before the wildcard or single match
     * character in the expression to prevent it from being used as a wildcard or single
     * match.  The escape character must not be used elsewhere in the expression.
     * For example, to match "35%" without activating % as a wildcard, set the escape character
     * to '$' and use the expression "35$%".     
     * @return Escape character, if not set will return {@link #NULL_ESCAPE_CHAR}
     */
    public char getEscapeChar() {
        return this.escapeChar;
    }

    /**
     * Set the escape character which can be used when the wildcard or single
     * character should be used literally.
     * @param escapeChar New escape character
     */
    public void setEscapeChar(char escapeChar) {
        this.escapeChar = escapeChar;
    }

    public MatchMode getMode() {
        return mode;
    }

    public void setMode(MatchMode mode) {
        this.mode = mode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.escapeChar;
        result = prime * result + ((this.leftExpression == null) ? 0 : this.leftExpression.hashCode());
        result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = prime * result + (this.negated ? 1231 : 1237);
        result = prime * result + ((this.rightExpression == null) ? 0 : this.rightExpression.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        MatchCriteria other = (MatchCriteria)obj;
        if (this.escapeChar != other.escapeChar) return false;
        if (this.leftExpression == null) {
            if (other.leftExpression != null) return false;
        } else if (!this.leftExpression.equals(other.leftExpression)) return false;
        if (this.mode != other.mode) return false;
        if (this.negated != other.negated) return false;
        if (this.rightExpression == null) {
            if (other.rightExpression != null) return false;
        } else if (!this.rightExpression.equals(other.rightExpression)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=0f89c892141b9a7e6acaf4cfc0d222f5 (do not edit this line) */

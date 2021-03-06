/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.query.parser;

import java.io.Reader;

import org.teiid.core.types.DataTypeManagerService;
import org.teiid.designer.annotation.Since;
import org.teiid.designer.runtime.version.spi.ITeiidServerVersion;
import org.teiid.designer.runtime.version.spi.TeiidServerVersion.Version;
import org.teiid.metadata.MetadataFactory;
import org.teiid.query.parser.TeiidNodeFactory.ASTNodes;
import org.teiid.query.sql.lang.CacheHint;
import org.teiid.query.sql.lang.Command;
import org.teiid.query.sql.lang.Criteria;
import org.teiid.query.sql.lang.LanguageObject;
import org.teiid.query.sql.lang.LeadingComment;
import org.teiid.query.sql.lang.TrailingComment;
import org.teiid.query.sql.proc.Statement;
import org.teiid.query.sql.symbol.Expression;

/**
 *
 */
public interface TeiidParser {

    /**
     * @return teiid version of this parser
     */
    ITeiidServerVersion getVersion();

    /**
     * @param teiidVersion
     */
    void setVersion(ITeiidServerVersion teiidVersion);

    /**
     * @return dataTypeManagerService
     */
    DataTypeManagerService getDataTypeService();

    /**
     * Reinitialise the parser against the new sql reader
     *
     * @param sql
     */
    void ReInit(Reader sql);

    /**
     * @param nodeType
     * 
     * @return instance of commonly used node
     */
    <T extends LanguageObject> T createASTNode(ASTNodes nodeType);

    /**
     * @param parseInfo
     *
     * @return command for trigger action
     * @throws Exception 
     */
    Command forEachRowTriggerAction(ParseInfo parseInfo) throws Exception;

    /**
     * Parse an expression
     *
     * @param info
     * @return the expression
     * @throws Exception
     */
    Expression expression(ParseInfo info) throws Exception;

    /**
     * Parse a command
     *
     * @param parseInfo
     * @return the command
     * @throws Exception
     */
    Command command(ParseInfo parseInfo) throws Exception;

    /**
     * Parse a designer command
     *
     * @param parseInfo
     * @return the command
     * @throws Exception
     */
    Command designerCommand(ParseInfo parseInfo) throws Exception;

    /**
     * Parse a criteria
     *
     * @param parseInfo
     * @return the criteria
     * @throws Exception
     */
    Criteria criteria(ParseInfo parseInfo) throws Exception;

    /**
     * Parse a statement
     *
     * @param info
     * @return the statement
     * @throws Exception
     */
    Statement statement(ParseInfo info) throws Exception;

    /**
     * Parse a select expression
     *
     * @param info
     * @return the select expression
     * @throws Exception
     */
    Expression selectExpression(ParseInfo info) throws Exception;

    /**
     * Parse a procedure command
     *
     * @param parseInfo
     * @return command
     * @throws Exception 
     */
    @Since(Version.TEIID_8_0)
    Command procedureBodyCommand(ParseInfo parseInfo) throws Exception;

    /**
     * @param factory
     * @throws Exception 
     */
    @Since(Version.TEIID_8_0)
    void parseMetadata(MetadataFactory factory) throws Exception;
    
    @Since(Version.TEIID_8_0)
    CacheHint getQueryCacheOption(String sql);

    @Since(Version.TEIID_8_0)
    LeadingComment getLeadingComment(String sql);

    @Since(Version.TEIID_8_0)
    TrailingComment getTrailingComment(String sql);
    
    @Since(Version.TEIID_8_0)
    String removeComments(String sql, LeadingComment leadingComment, TrailingComment trailingComment);
}

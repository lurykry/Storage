package edu.mansurov.storage.domain.expression;

import edu.mansurov.storage.domain.emums.QueryType;

import java.util.List;

/**
 * Parsed query
 */
public class Expression {
    private final QueryType queryType;
    private final List<String> tables;
    private final List<String> fields;
    private final List<String> functions;
    private final Where where;

    private Expression(QueryType queryType,
                      List<String> tables,
                      List<String> fields,
                      List<String> functions,
                      Where where) {
        this.queryType = queryType;
        this.tables = tables;
        this.fields = fields;
        this.functions = functions;
        this.where = where;
    }

    /**
     * Builds this object without where-clause
     * @param queryType type of the query
     * @param tables tables participating in the query
     * @param fields fields participating in the query
     * @param functions functions participating in the query
     */
    public static Expression withoutWhere(QueryType queryType, List<String> tables,
                                          List<String> fields, List<String> functions) {
        return new Expression(queryType, tables, fields, functions, Where.emptyWhere());
    }

    /**
     * Builds this object with where-clause
     * @param queryType type of the query
     * @param tables tables participating in the query
     * @param fields fields participating in the query
     * @param functions functions participating in the query
     * @param where the query condition
     */
    public static Expression withWhere(QueryType queryType, List<String> tables,
                                          List<String> fields, List<String> functions, Where where) {
        return new Expression(queryType, tables, fields, functions, where);
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public List<String> getTables() {
        return tables;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public Where getWhere() {
        return where;
    }
}

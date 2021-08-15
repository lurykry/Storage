package edu.mansurov.storage.service.query.parser;

import edu.mansurov.storage.domain.expression.Expression;

public interface SQLQueryParser {

    Expression parseQuery(String query);
}

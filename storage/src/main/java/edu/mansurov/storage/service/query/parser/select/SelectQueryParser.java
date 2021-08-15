package edu.mansurov.storage.service.query.parser.select;

import edu.mansurov.storage.domain.statement.SelectStatement;
import edu.mansurov.storage.domain.statement.base.Statement;

public class SelectQueryParser {

    public Statement parseSelectQuery(String query) {
        return new SelectStatement(query);
    }
}

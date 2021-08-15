package edu.mansurov.storage.service.query.validator.secondlvl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.domain.emums.QueryType;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.QuerySpecificValidator;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.insert.InsertSpecificValidator;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.select.SelectSpecificValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Validates query for the following rules:
 * - select specific constrains
 * - insert specific constrains
 * - update specific constrains
 * - delete specific constrains
 */
public class QuerySpecificValidation {
    private final Map<QueryType, QuerySpecificValidator> querySpecificValidations = new HashMap<>();

    {
        querySpecificValidations.put(QueryType.SELECT, new SelectSpecificValidator());
        querySpecificValidations.put(QueryType.INSERT, new InsertSpecificValidator());
    }

    public ValidatorResponse querySpecificValidation(QueryType command, String query) {
        QuerySpecificValidator validator = querySpecificValidations.get(command);
        return validator.querySpecificValidation(query);
    }
}

package edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.insert;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.QuerySpecificValidator;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.insert.impl.InsertSpecificConstrains;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Contains insert specific constrains
 * Checks if the query meets the constrains
 */
public class InsertSpecificValidator implements QuerySpecificValidator {
    private final List<Function<String, ValidatorResponse>> insertSpecificConstrains = new ArrayList<>();

    {
        insertSpecificConstrains.add(InsertSpecificConstrains::insertIntoConstrain);
    }

    public ValidatorResponse querySpecificValidation(String query) {
        ValidatorResponse response = ValidatorResponse.validationSucceeded();

        for(Function<String, ValidatorResponse> constrain : insertSpecificConstrains) {
            response = constrain.apply(query);
            if(!response.isQueryValid()) return response;
        }

        return response;
    }
}

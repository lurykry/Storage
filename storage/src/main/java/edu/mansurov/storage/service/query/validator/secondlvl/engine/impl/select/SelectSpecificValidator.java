package edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.select;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.QuerySpecificValidator;
import edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.select.impl.SelectSpecificConstrains;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Contains select specific constrains
 * Checks if the query meets the constrains
 */
public class SelectSpecificValidator implements QuerySpecificValidator {
    private final List<Function<String, ValidatorResponse>> selectSpecificConstrains = new ArrayList<>();

    {
        selectSpecificConstrains.add(SelectSpecificConstrains::selectFromConstrain);
    }

    public ValidatorResponse querySpecificValidation(String query) {
        ValidatorResponse response = ValidatorResponse.validationSucceeded();

        for(Function<String, ValidatorResponse> constrain : selectSpecificConstrains) {
            response = constrain.apply(query);
            if(!response.isQueryValid()) return response;
        }

        return response;
    }
}

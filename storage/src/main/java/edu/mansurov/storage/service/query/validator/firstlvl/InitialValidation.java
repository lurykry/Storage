package edu.mansurov.storage.service.query.validator.firstlvl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.InitialValidator;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.InputPresenceValidator;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.QueryKeyWordsValidator;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator.ParenthesesValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates query for the following rules:
 * - input presence
 * - input is a query
 * - parenthesis matched and in order
 */
public class InitialValidation {
    private final List<InitialValidator> initialValidators = new ArrayList<>();

    {
        initialValidators.add(new InputPresenceValidator());
        initialValidators.add(new QueryKeyWordsValidator());
        initialValidators.add(new ParenthesesValidator());
    }

    public ValidatorResponse initialValidation(String query) {
        ValidatorResponse response = ValidatorResponse.validationSucceeded();

        for(InitialValidator validator : initialValidators) {
            response = validator.initialValidation(query);
            if(!response.isQueryValid()) return response;
        }

        return response;
    }
}

package edu.mansurov.storage.service.query.validator.firstlvl.engine.impl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.domain.query.QueryKeyWords;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.InitialValidator;

public class QueryKeyWordsValidator implements InitialValidator {

    //TODO: усложнить логику!
    /**
     * Validates if the input is a query
     * @return true, if the input string is a query
     */
    @Override
    public ValidatorResponse initialValidation(String query) {
        boolean containsKeyWords = QueryKeyWords.containsKeyWords(query);
        return containsKeyWords
                ? ValidatorResponse.validationSucceeded()
                : ValidatorResponse.validationFailed("The input isn't a query!");
    }
}

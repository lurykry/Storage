package edu.mansurov.storage.service.query.validator.firstlvl.engine.impl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.InitialValidator;
import org.apache.commons.lang3.StringUtils;

public class InputPresenceValidator implements InitialValidator {

    /**
     * Validates if the input string isn't empty and contains enough symbols to be a query
     * @return true, if the input string isn't empty and contains enough symbols to be a query
     */
    @Override
    public ValidatorResponse initialValidation(String query) {
        boolean isValid = StringUtils.isNotBlank(query);
        return isValid
                ? ValidatorResponse.validationSucceeded()
                : ValidatorResponse.validationFailed("The input is null or contains no symbols!");
    }
}

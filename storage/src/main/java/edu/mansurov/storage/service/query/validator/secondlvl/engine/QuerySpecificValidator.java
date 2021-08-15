package edu.mansurov.storage.service.query.validator.secondlvl.engine;

import edu.mansurov.storage.domain.validator.ValidatorResponse;

public interface QuerySpecificValidator {

    ValidatorResponse querySpecificValidation(String query);
}

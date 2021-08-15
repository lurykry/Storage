package edu.mansurov.storage.service.query.validator.firstlvl.engine;

import edu.mansurov.storage.domain.validator.ValidatorResponse;

public interface InitialValidator {

    ValidatorResponse initialValidation(String query);
}

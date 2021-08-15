package edu.mansurov.storage.service.query.validator;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.domain.emums.QueryType;
import edu.mansurov.storage.service.query.definer.QueryTypeDefiner;
import edu.mansurov.storage.service.query.validator.firstlvl.InitialValidation;
import edu.mansurov.storage.service.query.validator.secondlvl.QuerySpecificValidation;

/**
 * Runs query through first and second lvl validations
 */
public class BeforeParsingQueryValidator {
    private final InitialValidation initialValidation = new InitialValidation();
    private final QuerySpecificValidation querySpecificValidation = new QuerySpecificValidation();
    private final QueryTypeDefiner queryTypeDefiner = new QueryTypeDefiner();

    public ValidatorResponse validateQuery(String query) {
        ValidatorResponse initialValidationResponse = this.initialValidation.initialValidation(query);
        if(!initialValidationResponse.isQueryValid()) return initialValidationResponse;

        QueryType command = queryTypeDefiner.defineQueryType(query);
        return querySpecificValidation.querySpecificValidation(command, query);
    }
}

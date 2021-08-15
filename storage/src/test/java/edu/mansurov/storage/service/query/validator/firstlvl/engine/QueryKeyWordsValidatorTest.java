package edu.mansurov.storage.service.query.validator.firstlvl.engine;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.QueryKeyWordsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryKeyWordsValidatorTest {
    private final InitialValidator queryKeyWordsValidator = new QueryKeyWordsValidator();

    @Test
    public void testQueryKeyWordsValidatorNoQueryWords() {
        ValidatorResponse response = queryKeyWordsValidator.initialValidation("String with no 'query words'");
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input isn't a query!");
    }

    @Test
    public void testQueryKeyWordsValidatorQueryWordsPresent() {
        ValidatorResponse response = queryKeyWordsValidator.initialValidation(
                "This is 2nd lvl validation; Checking for the 'key' words or 'key' word pairs like SELECT FROM");
        Assertions.assertTrue(response.isQueryValid());
    }
}

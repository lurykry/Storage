package edu.mansurov.storage.service.query.validator.firstlvl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InitialValidationTest {
    private final InitialValidation initialValidation = new InitialValidation();

    /**
     * At initial validation stage, the query is considered valid if it meets the following rules:
     * - Input is nether null nor empty
     * - Input contains at least one 'key' word
     * - Input either contains matched parenthesis coming in order or contains no parenthesis at all
     */
    @Test
    public void testInitialValidationValidQuery() {
        ValidatorResponse response = initialValidation.initialValidation("SELECT * FROM t1");
        Assertions.assertTrue(response.isQueryValid());
    }

    @Test
    public void testInitialValidationNullQuery() {
        ValidatorResponse response = initialValidation.initialValidation(null);
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input is null or contains no symbols!");
    }

    @Test
    public void testInitialValidationEmptyQuery() {
        ValidatorResponse response = initialValidation.initialValidation("");
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input is null or contains no symbols!");
    }

    @Test
    public void testInitialValidationNoKeyWordQuery() {
        ValidatorResponse response = initialValidation.initialValidation("No key words here");
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input isn't a query!");
    }

    @Test
    public void testInitialValidationBadParenthesis() {
        ValidatorResponse response = initialValidation.initialValidation("SELECT f1, count(] AS counter FROM t1");
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "Unexpected token ']' at position 17");
    }
}

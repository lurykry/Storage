package edu.mansurov.storage.service.query.validator.firstlvl.engine;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.parenthesesValidator.ParenthesesValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParenthesesValidatorTest {
    private final ParenthesesValidator parenthesesValidator = new ParenthesesValidator();

    @Test
    public void testParenthesesValidatorNoBracketQuery() {
        String validQuery = "SELECT f1 FROM t1";
        ValidatorResponse response = parenthesesValidator.initialValidation(validQuery);
        Assertions.assertTrue(response.isQueryValid());
    }

    @Test
    public void testParenthesesValidatorValidQuery() {
        String validQuery = "SELECT f1[counter], (SELECT count(id) FROM t2) AS f2 FROM t1 WHERE f1 <> f2";
        ValidatorResponse response = parenthesesValidator.initialValidation(validQuery);
        Assertions.assertTrue(response.isQueryValid());
    }

    @Test
    public void testParenthesesValidatorWrongBracketsOrder() {
        String validQuery = "SELECT f1[counter, (]SELECT count(id) FROM t2) AS f2 FROM t1 WHERE f1 <> f2";
        ValidatorResponse response = parenthesesValidator.initialValidation(validQuery);
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "Unexpected token ']' at position 20");
    }

    @Test
    public void testParenthesesValidatorLackingClosingBrackets() {
        String validQuery = "SELECT f1[counter], (SELECT count(id) FROM t2 AS f2 FROM t1 WHERE f1 <> f2";
        ValidatorResponse response = parenthesesValidator.initialValidation(validQuery);
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "Expecting closing symbols for '(' at position 20");
    }

    @Test
    public void testParenthesesValidatorLackingOpeningBrackets() {
        String validQuery = "SELECT f1counter], (SELECT count(id) FROM t2 AS f2 FROM t1 WHERE f1 <> f2";
        ValidatorResponse response = parenthesesValidator.initialValidation(validQuery);
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "Unexpected token ']' at position 16");
    }
}

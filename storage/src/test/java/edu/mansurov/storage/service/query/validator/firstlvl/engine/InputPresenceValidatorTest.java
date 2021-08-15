package edu.mansurov.storage.service.query.validator.firstlvl.engine;

import edu.mansurov.storage.domain.validator.ValidatorResponse;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.InitialValidator;
import edu.mansurov.storage.service.query.validator.firstlvl.engine.impl.InputPresenceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputPresenceValidatorTest {
    private final InitialValidator inputPresenceValidator = new InputPresenceValidator();

    @Test
    public void testInputPresenceValidatorNullString() {
        ValidatorResponse response = inputPresenceValidator.initialValidation(null);
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input is null or contains no symbols!");
    }

    @Test
    public void testInputPresenceValidatorEmptyString() {
        ValidatorResponse response = inputPresenceValidator.initialValidation("");
        Assertions.assertFalse(response.isQueryValid());
        Assertions.assertEquals(response.getReason(), "The input is null or contains no symbols!");
    }

    @Test
    public void testInputPresenceValidatorValidString() {
        ValidatorResponse response = inputPresenceValidator.initialValidation("This is 1st lvl validator; Just checking for input presence");
        Assertions.assertTrue(response.isQueryValid());
    }
}

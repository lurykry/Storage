package edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.select.impl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Specific rules for SELECT queries validation
 */
public class SelectSpecificConstrains {
    /**
     * Correct simple select query:
     * - SELECT must be at the begging
     * - SELECT is followed by any number of fields
     * - FROM is preceded by a space and followed by table names
     */
    private static final String SELECT_FROM_LOOKAHEAD_REGEX = "^(?=SELECT\\s+\\S+.*\\s+FROM\\s+\\S+)";

    public static ValidatorResponse selectFromConstrain(String query) {
        Pattern pattern = Pattern.compile(SELECT_FROM_LOOKAHEAD_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);

        return matcher.find()
                ? ValidatorResponse.validationSucceeded()
                : ValidatorResponse.validationFailed("Incorrect syntax of select query!");
    }
}

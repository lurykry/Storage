package edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.insert.impl;

import edu.mansurov.storage.domain.validator.ValidatorResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Specific rules for INSERT queries validation
 */
public class InsertSpecificConstrains {
    private static final String INSERT_INTO_LOOKAHEAD_REGEX = "^(?=INSERT (IN|INTO)\\s+\\S+.*)";
    private static final String INSERT_INTO_VALUES_LOOKAHEAD_REGEX = "";

    public static ValidatorResponse insertIntoConstrain(String query) {
        Pattern pattern = Pattern.compile(INSERT_INTO_LOOKAHEAD_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);

        return matcher.find()
                ? ValidatorResponse.validationSucceeded()
                : ValidatorResponse.validationFailed("Incorrect syntax of insert query!");
    }
}

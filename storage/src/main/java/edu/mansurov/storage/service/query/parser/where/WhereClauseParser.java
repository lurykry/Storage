package edu.mansurov.storage.service.query.parser.where;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhereClauseParser {
    /**
     * arg1 =,<>,!=,<=,>=,<,> arg2
     */
    private static final String ARGS_AND_OPERATOR_PATTERN = "[a-zA-Z_][\\w()]*\\s*[<>=]{1,2}\\s*[a-zA-Z_][\\w()]*";

    public boolean parseWhere(String where) {
        Pattern pattern = Pattern.compile(ARGS_AND_OPERATOR_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(where);

        return matcher.find();
    }
}

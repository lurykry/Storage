package edu.mansurov.classinstantiating.service.helpers;

import edu.mansurov.classinstantiating.exceptions.NoActionForTheGivenTypeFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class FieldBuilder {
    private static final String STRING_FULL_NAME = "java.lang.String";
    private static final String INTEGER_FULL_NAME = "java.lang.Integer";
    private static final String DOUBLE_FULL_NAME = "java.lang.Double";
    private static final String BOOLEAN_FULL_NAME = "java.lang.Boolean";
    private static final String LONG_FULL_NAME = "java.lang.Long";

    private final Map<String, BiFunction<String, String, String>> actions = new HashMap<>();

    {
        actions.put(STRING_FULL_NAME, this::stringValue);
        actions.put(INTEGER_FULL_NAME, this::integerValue);
        actions.put(DOUBLE_FULL_NAME, this::doubleValue);
        actions.put(BOOLEAN_FULL_NAME, this::booleanValue);
        actions.put(LONG_FULL_NAME, this::longValue);
    }

    public String buildField(String type, String name, String value) {
        BiFunction<String, String, String> action = actions.get(type);

        if(action == null) throw new NoActionForTheGivenTypeFoundException("No action for the type " + type + " found!");
        else return action.apply(name, value);
    }

    private String stringValue(String name, String value) {
        return " " + STRING_FULL_NAME + " " + name + " = " + "\"" + value + "\"" +";\r\n";
    }

    private String integerValue(String name, String value) {
        return " " + INTEGER_FULL_NAME + " " + name + " = " + value + ";\r\n";
    }

    private String doubleValue(String name, String value) {
        return " " + DOUBLE_FULL_NAME + " " + name + " = " + value + ";\r\n";
    }

    private String booleanValue(String name, String value) {
        return " " + BOOLEAN_FULL_NAME + " " + name + " = " + value + ";\r\n";
    }

    private String longValue(String name, String value) {
        return " " + LONG_FULL_NAME + " " + name + " = " + value + ";\r\n";
    }
}

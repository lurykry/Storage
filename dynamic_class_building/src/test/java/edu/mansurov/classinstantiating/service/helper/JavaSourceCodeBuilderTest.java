package edu.mansurov.classinstantiating.service.helper;

import edu.mansurov.classinstantiating.domain.JavaSourceCode;
import edu.mansurov.classinstantiating.service.helpers.JavaSourceCodeBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JavaSourceCodeBuilderTest {
    private final JavaSourceCodeBuilder builder = new JavaSourceCodeBuilder();

    @Test
    public void testBuildSourceCode() {
        JavaSourceCode sourceCode = builder.build(createObjectMap());
        System.out.println(sourceCode.getSource());
    }

    private Map<String, Object> createObjectMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("className", "edu.mansurov.Person");
        objectMap.put("id", "1");
        objectMap.put("name", "Jack");
        objectMap.put("details", createDetails());

        return objectMap;
    }

    private Map<String, Object> createDetails() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("age", "27");
        details.put("date of birth", "21.01.1994");

        return details;
    }
}

package edu.mansurov.classinstantiating.service;

import edu.mansurov.classinstantiating.domain.JavaSourceCode;

import java.util.Map;

public interface JsonParser {

    JavaSourceCode toJavaSourceCodeParsing(String json);
    Object toObjectParsing(String json);
    Map<String, Object> toMapParsing(String json);
}

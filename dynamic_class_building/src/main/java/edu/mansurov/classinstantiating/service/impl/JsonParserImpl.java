package edu.mansurov.classinstantiating.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mansurov.classinstantiating.domain.JavaSourceCode;
import edu.mansurov.classinstantiating.exceptions.JsonParsingException;
import edu.mansurov.classinstantiating.service.JsonParser;
import edu.mansurov.classinstantiating.service.helpers.JavaSourceCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonParserImpl implements JsonParser {
    private final Logger logger = LoggerFactory.getLogger(JsonParserImpl.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final JavaSourceCodeBuilder javaSourceCodeBuilder = new JavaSourceCodeBuilder();

    @Override
    public JavaSourceCode toJavaSourceCodeParsing(String json) {
        Map<String, Object> objectMap = handleMapParse(json);
        return javaSourceCodeBuilder.build(objectMap);
    }

    @Override
    public Object toObjectParsing(String json) {
        Map<String, Object> objectMap = handleMapParse(json);
        StringBuilder source = new StringBuilder();

        String classFullName = objectMap.get("className").toString();

        return null;
    }

    public Map<String, Object> toMapParsing(String json) {
        return handleMapParse(json);
    }

    private Map<String, Object> handleMapParse(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            logger.error("An error has occurred while processing the following json: {}", json);
            throw new JsonParsingException(e.getMessage(), e);
        }
    }
}

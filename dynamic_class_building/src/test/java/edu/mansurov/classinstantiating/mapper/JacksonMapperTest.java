package edu.mansurov.classinstantiating.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Testing Json serialization into Java Map
 */
public class JacksonMapperTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void simpleJsonToJavaMap() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        Map<String, Object> objectMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        Assertions.assertTrue(objectMap.containsKey("color"));
        Assertions.assertEquals("Black", objectMap.get("color"));
    }

    @Test
    public void complicatedJsonToJavaMap() throws JsonProcessingException {
        String json = "{\n" +
                "    \"name\": \"Pear yPhone 72\",\n" +
                "    \"category\": \"cellphone\",\n" +
                "    \"details\": {\n" +
                "        \"displayAspectRatio\": {\"id\": \"15\", \"tag\": \"unique_tag\"},\n" +
                "        \"audioConnector\": \"none\"\n" +
                "    }\n" +
                "}";
        Map<String, Object> objectMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        Assertions.assertTrue(objectMap.containsKey("details"));
        Assertions.assertTrue(((LinkedHashMap) objectMap.get("details")).containsKey("displayAspectRatio"));

        LinkedHashMap<String, Object> displayAspectRatio = (LinkedHashMap<String, Object>) ((LinkedHashMap<String, Object>) objectMap.get("details")).get("displayAspectRatio");
        Assertions.assertTrue(displayAspectRatio.containsKey("id"));
        Assertions.assertEquals("unique_tag", displayAspectRatio.get("tag"));
    }
}

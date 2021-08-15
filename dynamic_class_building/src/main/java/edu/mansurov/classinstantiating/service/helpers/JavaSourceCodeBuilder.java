package edu.mansurov.classinstantiating.service.helpers;

import edu.mansurov.classinstantiating.domain.JavaSourceCode;

import java.util.Map;

public class JavaSourceCodeBuilder {
    private final FieldBuilder fieldBuilder = new FieldBuilder();

    public JavaSourceCode build(Map<String, Object> objectMap) {
        StringBuilder source = new StringBuilder();

        buildBasic(source, objectMap);
        buildFields(source, objectMap);
        buildGettersAndSetters(source, objectMap);
        buildEndOfClass(source);

        return new JavaSourceCode(source.toString());
    }

    private void buildFields(StringBuilder source, Map<String, Object> objectMap) {
        for(Map.Entry<String, Object> field : objectMap.entrySet())
            source.append(getField(field));
    }

    private String getField(Map.Entry<String, Object> field) {
            String name = field.getKey();
            String value = field.getValue().toString();
            return fieldBuilder.buildField(name.getClass().getTypeName(), name, value);
    }

    private void buildGettersAndSetters(StringBuilder source, Map<String, Object> objectMap) {
        for(Map.Entry<String, Object> field : objectMap.entrySet()) {
            source.append(buildGetter(field));
            source.append(buildSetter(field));
        }
    }

    private String buildGetter(Map.Entry<String, Object> field) {
        String name = field.getKey();
        String className = field.getValue().getClass().getName();

        return "\r\n public " + className + " get" + capitalizeFirstLetter(name) + "() {\r\n" + "  return " + name + ";\r\n"
                + " }\r\n";
    }

    private String buildSetter(Map.Entry<String, Object> field) {
        String name = field.getKey();
        String className = field.getValue().getClass().getName();

        return "\r\n public void set" + capitalizeFirstLetter(name) + "(" + className + " " + name + ") {\r\n" + "  this." + name
                + " = " + name + ";\r\n" + " }\r\n";
    }

    private void buildBasic(StringBuilder source, Map<String, Object> objectMap) {
        String classFullName = objectMap.get("className").toString();
        String classShortName = getShortClassNameFromFullName(classFullName);
        String packageName = getPackageNameFromFullName(classFullName);

        source.append("package " + packageName + ";\r\r\n");
        source.append("import java.io.Serializable;\r\n");
        source.append("\r\n");
        source.append("public class " + classShortName + " implements Serializable  {\r\n");
        source.append("\r\n");
        source.append(" private static final long serialVersionUID = 1L;\r\n");
        source.append("\r\n");
    }

    private void buildEndOfClass(StringBuilder source) {
        source.append("\r\n}\r\n");
    }

    private String getShortClassNameFromFullName(String fullName) {
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }

    private String getPackageNameFromFullName(String fullName) {
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    private static String capitalizeFirstLetter(String fieldName) {
        if (fieldName != null && fieldName.length() > 0) {
            return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return fieldName;
    }
}

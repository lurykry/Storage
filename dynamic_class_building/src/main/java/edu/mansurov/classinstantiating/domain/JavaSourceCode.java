package edu.mansurov.classinstantiating.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaSourceCode {
    private final String source;
    private final List<JavaSourceCode> objectSources = new ArrayList<>();

    public JavaSourceCode(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public List<JavaSourceCode> getObjectSources() {
        return Collections.unmodifiableList(objectSources);
    }
}

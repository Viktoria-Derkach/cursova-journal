package com.journal.factories;

import com.journal.filters.*;

import java.util.Map;

public final class FilterFactory {

    private static FilterFactory factory;

    private FilterFactory() {
    }

    public static FilterFactory getInstance() {
        if (factory == null)
            factory = new FilterFactory();
        return factory;
    }

    public Filter<?> getEntityFilter(String entityName, Map<String, String> filters) {
        return switch (entityName) {
            case "student" -> new StudentFilter(filters);
            case "teacher" -> new TeacherFilter(filters);
            case "subject" -> new SubjectFilter(filters);
            case "direction" -> new DirectionFilter(filters);
            case "chair" -> new ChairFilter(filters);
            default -> null;
        };
    }
}

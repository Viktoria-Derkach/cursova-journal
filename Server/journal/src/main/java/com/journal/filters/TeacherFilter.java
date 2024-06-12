package com.journal.filters;

import com.journal.entities.Subject;
import com.journal.entities.Teacher;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeacherFilter extends Filter<Teacher> {

    public TeacherFilter(Map<String, String> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public List<Teacher> filtrate(List<Teacher> entities) {
        Stream<Teacher> stream = entities.stream();
        for (String key : filters.keySet()) {
            if (!filters.get(key).equals("All"))
                switch (key) {
                    case "faculty" -> stream = stream.filter(e -> e.getFaculty().getName().equals(filters.get(key)));
                    case "chair" -> stream = stream.filter(e -> e.getChair().getName().equals(filters.get(key)));
                    case "subject" -> stream = stream.filter(e -> e.getSubjects().stream().map(Subject::getName)
                            .anyMatch(filters.get(key)::equals));
                }
        }
        return stream.collect(Collectors.toList());
    }
}
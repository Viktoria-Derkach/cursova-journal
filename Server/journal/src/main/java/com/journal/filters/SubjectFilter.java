package com.journal.filters;

import com.journal.entities.Subject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubjectFilter extends Filter<Subject> {
    public SubjectFilter(Map<String, String> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public List<Subject> filtrate(List<Subject> entities) {
        Stream<Subject> stream = entities.stream();
        for (String key : filters.keySet()) {
            if (!filters.get(key).equals("All"))
                switch (key) {
                    case "faculty" -> stream = stream.filter(e -> e.getFaculty().getName().equals(filters.get(key)));
                    case "direction" -> stream = stream.filter(e -> e.getDirection().getName().equals(filters.get(key)));
                    case "course" -> stream = stream.filter(e -> e.getCourse().equals(Integer.parseInt(filters.get(key))));
                    case "chair" -> stream = stream.filter(e -> e.getChair().getName().equals((filters.get(key))));
                }
        }
        return stream.collect(Collectors.toList());
    }
}

package com.journal.filters;

import com.journal.entities.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentFilter extends Filter<Student> {

    public StudentFilter(Map<String, String> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public List<Student> filtrate(List<Student> entities) {
        Stream<Student> stream = entities.stream();
        for (String key : filters.keySet()) {
            if (!filters.get(key).equals("All"))
                switch (key) {
                    case "faculty" -> stream = stream.filter(e -> e.getFaculty().getName().equals(filters.get(key)));
                    case "direction" -> stream = stream.filter(e -> e.getDirection().getName().equals(filters.get(key)));
                    case "course" -> stream = stream.filter(e -> e.getCourse().equals(Integer.parseInt(filters.get(key))));
                    case "group" -> stream = stream.filter(e -> e.getStudentGroup().equals(Integer.parseInt(filters.get(key))));
                }
        }
        return stream.collect(Collectors.toList());
    }
}

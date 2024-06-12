package com.journal.filters;

import com.journal.entities.Direction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectionFilter extends Filter<Direction> {
    public DirectionFilter(Map<String, String> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public List<Direction> filtrate(List<Direction> entities) {
        Stream<Direction> stream = entities.stream();
        for (String key : filters.keySet()) {
            if (!filters.get(key).equals("All"))
                if ("faculty".equals(key)) {
                    stream = stream.filter(e -> e.getFaculty().getName().equals(filters.get(key)));
                }
        }
        return stream.collect(Collectors.toList());
    }
}

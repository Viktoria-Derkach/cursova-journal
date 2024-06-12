package com.journal.filters;

import com.journal.entities.Chair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChairFilter extends Filter<Chair> {

    public ChairFilter(Map<String, String> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public List<Chair> filtrate(List<Chair> entities) {
        Stream<Chair> stream = entities.stream();
        for (String key : filters.keySet()) {
            if (!filters.get(key).equals("All"))
                if ("faculty".equals(key)) {
                    stream = stream.filter(e -> e.getFaculty().getName().equals(filters.get(key)));
                }
        }
        return stream.collect(Collectors.toList());
    }
}

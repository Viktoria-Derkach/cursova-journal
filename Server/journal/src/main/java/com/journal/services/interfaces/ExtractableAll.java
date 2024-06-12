package com.journal.services.interfaces;

import com.journal.filters.Filter;

import java.util.List;
import java.util.Map;

public interface ExtractableAll<T> {
    List<Map<String, Object>> getAll(Filter<?> filter);

    List<T> getAllEntities(Filter<?> filter);
}

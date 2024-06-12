package com.journal.services.interfaces;

import com.journal.filters.Filter;

import java.util.Set;

public interface ExtractableNames {
    Set<String> getEntitiesName(Filter<?> filter);
}

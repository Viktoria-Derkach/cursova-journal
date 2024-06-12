package com.journal.filters;

import java.util.List;
import java.util.Map;

public abstract class Filter<T> {

    protected Map<String, String> filters;

    public abstract List<T> filtrate(List<T> entities);

}

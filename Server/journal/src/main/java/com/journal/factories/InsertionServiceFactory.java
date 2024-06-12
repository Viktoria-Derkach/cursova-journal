package com.journal.factories;

import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class InsertionServiceFactory {

    private final Map<String, Insertable> services;

    @Autowired
    private InsertionServiceFactory(List<Insertable> services) {
        this.services = services.stream().collect(Collectors.toUnmodifiableMap(service -> {
            String st = String.valueOf(service.getClass());
            return st.replace("class com.journal.services.", "");
        }, Function.identity()));
    }

    public Insertable getService(String name) {
        return services.get(name);
    }
}

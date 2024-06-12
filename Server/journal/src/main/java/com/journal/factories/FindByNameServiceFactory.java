package com.journal.factories;

import com.journal.services.interfaces.FindableByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FindByNameServiceFactory {
    private final Map<String, FindableByName> services;

    @Autowired
    private FindByNameServiceFactory(List<FindableByName> services) {
        this.services = services.stream().collect(Collectors.toUnmodifiableMap(service -> {
            String st = String.valueOf(service.getClass());
            return st.replace("class com.journal.services.", "");
        }, Function.identity()));
    }

    public FindableByName<?> getService(String name) {
        return services.get(name);
    }
}

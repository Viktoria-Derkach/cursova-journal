package com.journal.factories;

import com.journal.services.interfaces.ExtractableAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExtractAllServiceFactory {
    private final Map<String, ExtractableAll> services;

    @Autowired
    private ExtractAllServiceFactory(List<ExtractableAll> services) {
        this.services = services.stream().collect(Collectors.toUnmodifiableMap(service -> {
            String st = String.valueOf(service.getClass());
            return st.replace("class com.journal.services.", "");
        }, Function.identity()));
    }

    public ExtractableAll getService(String name) {
        return services.get(name);
    }
}

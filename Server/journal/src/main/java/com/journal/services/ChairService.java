package com.journal.services;

import com.journal.entities.Chair;
import com.journal.filters.Filter;
import com.journal.repositories.ChairRepository;
import com.journal.services.interfaces.ExtractableNames;
import com.journal.services.interfaces.FindableByName;
import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChairService implements Insertable<Chair>, FindableByName<Chair>, ExtractableNames {

    private final ChairRepository chairRepository;

    @Autowired
    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    @Override
    public boolean InsertEntity(Chair entity) {
        chairRepository.save(entity);
        return true;
    }

    @Override
    public Chair findByName(String name) {
        return chairRepository.findByName(name);
    }

    @Override
    public Set<String> getEntitiesName(Filter filter) {
        List<Chair> filtrated = filter.filtrate(chairRepository.findAll());
        return filtrated.stream().map(Chair::getName).collect(Collectors.toSet());
    }
}

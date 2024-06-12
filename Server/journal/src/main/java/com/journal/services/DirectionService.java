package com.journal.services;

import com.journal.entities.Direction;
import com.journal.filters.Filter;
import com.journal.repositories.DirectionRepository;
import com.journal.services.interfaces.Countable;
import com.journal.services.interfaces.ExtractableNames;
import com.journal.services.interfaces.FindableByName;
import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DirectionService implements Insertable<Direction>, FindableByName<Direction>, ExtractableNames, Countable {

    private final DirectionRepository directionRepository;

    @Autowired
    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    public boolean InsertEntity(Direction entity) {
        directionRepository.save(entity);
        return true;
    }

    @Override
    public Direction findByName(String name) {
        return directionRepository.findByName(name);
    }

    @Override
    public Set<String> getEntitiesName(Filter filter) {
        List<Direction> filtrated = filter.filtrate(directionRepository.findAll());
        return filtrated.stream().map(Direction::getName).collect(Collectors.toSet());
    }

    @Override
    public long count() {
        return directionRepository.count();
    }
}

package com.journal.services;

import com.journal.entities.Faculty;
import com.journal.filters.Filter;
import com.journal.repositories.FacultyRepository;
import com.journal.services.interfaces.Countable;
import com.journal.services.interfaces.ExtractableNames;
import com.journal.services.interfaces.FindableByName;
import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyService implements Insertable<Faculty>, FindableByName<Faculty>, ExtractableNames, Countable {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public boolean InsertEntity(Faculty entity) {
        facultyRepository.save(entity);
        return true;
    }

    @Override
    public Faculty findByName(String name) {
        Faculty faculty = facultyRepository.findByName(name);
        return faculty;
    }

    @Override
    public Set<String> getEntitiesName(Filter<?> filter) {
        facultyRepository.count();
        return facultyRepository.findAll().stream().map(Faculty::getName).collect(Collectors.toSet());
    }

    @Override
    public long count() {
        return facultyRepository.count();
    }
}

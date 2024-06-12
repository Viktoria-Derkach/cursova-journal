package com.journal.services;

import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.filters.Filter;
import com.journal.repositories.SubjectRepository;
import com.journal.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectService implements Insertable<Subject>, ExtractableNames,
        ExtractableAll<Subject>, Deletable, FindableByName<Subject> {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public boolean InsertEntity(Subject entity) {
        subjectRepository.save(entity);
        return true;
    }

    @Override
    public Set<String> getEntitiesName(Filter filter) {
        List<Subject> filtrated = filter.filtrate(subjectRepository.findAll());
        return filtrated.stream().map(Subject::getName).collect(Collectors.toSet());
    }

    @Override
    public List<Map<String, Object>> getAll(Filter filter) {
        List<Subject> subjectList = filter.filtrate(subjectRepository.findAll());
        return subjectList.stream().map(subject -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", subject.getId());
            map.put("name", subject.getName());
            map.put("faculty", subject.getFaculty().getName());
            map.put("hours", subject.getHours());
            map.put("teacher", subject.getTeachers().stream().map(Teacher::getName).collect(Collectors.toList()));
            map.put("direction", subject.getDirection().getName());
            map.put("course", subject.getCourse());
            map.put("group", subject.getStudentGroup());
            map.put("chair", subject.getChair().getName());
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Subject> getAllEntities(Filter filter) {
        return filter.filtrate(subjectRepository.findAll());
    }

    @Override
    public void delete(String id) {
        Subject subjectToDelete = subjectRepository.findById(id).get();
        subjectToDelete.getStudents().clear();
        subjectToDelete.getPoints().clear();
        subjectToDelete.getTeachers().clear();
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
    }
}

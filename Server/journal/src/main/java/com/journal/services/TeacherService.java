package com.journal.services;

import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.filters.Filter;
import com.journal.repositories.TeacherRepository;
import com.journal.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherService implements Authorizable, Insertable<Teacher>,
        ExtractableNames, ExtractableAll<Teacher>, Deletable, Countable,
        FindableByName<Teacher>, FindableById<Teacher> {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public boolean InsertEntity(Teacher entity) {
        teacherRepository.save(entity);
        return true;
    }

    @Override
    public String getUserPasswordByEmail(String email) {
        return teacherRepository.getPasswordByEmail(email).orElseGet(() -> "");
    }

    @Override
    public Map<String, Object> getUserByEmail(String email) {
        Teacher teacher = teacherRepository.getUserByEmail(email);
        Map<String, Object> map = new HashMap<>();
        map.put("id", teacher.getId());
        return map;
    }

    @Override
    public Set<String> getEntitiesName(Filter filter) {
        List<Teacher> filtrated = filter.filtrate(teacherRepository.findAll());
        return filtrated.stream().map(Teacher::getName).collect(Collectors.toSet());
    }

    @Override
    public List<Map<String, Object>> getAll(Filter filter) {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> teacherList = filter.filtrate(teachers);
        return teacherList.stream().map(teacher -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", teacher.getId());
            map.put("name", teacher.getName());
            map.put("faculty", teacher.getFaculty().getName());
            map.put("chair", teacher.getChair().getName());
            map.put("subjects", teacher.getSubjects().stream().map(Subject::getName).collect(Collectors.toSet()));
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> getAllEntities(Filter filter) {
        return null;
    }

    @Override
    public void delete(String id) {
        Teacher teacherToDelete = teacherRepository.findById(id).get();
        teacherToDelete.getSubjects().clear();
        teacherToDelete.getAds().clear();
        teacherRepository.deleteById(id);
    }

    @Override
    public long count() {
        return teacherRepository.count();
    }

    @Override
    public Teacher findByName(String name) {
        return teacherRepository.findByName(name);
    }

    @Override
    public Teacher find(String id) {
        return teacherRepository.findById(id).get();
    }
}

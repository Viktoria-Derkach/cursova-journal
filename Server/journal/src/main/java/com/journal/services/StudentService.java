package com.journal.services;

import com.journal.entities.Student;
import com.journal.filters.Filter;
import com.journal.repositories.StudentRepository;
import com.journal.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService implements Authorizable, Insertable<Student>, ExtractableAll<Student>,
        Transferable, Deletable, Countable, FindableById<Student> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean InsertEntity(Student entity) {
        studentRepository.save(entity);
        return true;
    }

    @Override
    public String getUserPasswordByEmail(String email) {
        return studentRepository.getPasswordByEmail(email).orElseGet(() -> "");
    }

    @Override
    public Map<String, Object> getUserByEmail(String email) {
        Student student = studentRepository.getUserByEmail(email);
        Map<String, Object> map = new HashMap<>();
        map.put("id", student.getId());
        return map;
    }

    @Override
    public List<Map<String, Object>> getAll(Filter filter) {
        List<Student> studentList = filter.filtrate(studentRepository.findAll());
        return studentList.stream().map(student -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", student.getId());
            map.put("name", student.getName());
            map.put("faculty", student.getFaculty().getName());
            map.put("number", student.getNumber());
            map.put("direction", student.getDirection().getName());
            map.put("course", student.getCourse());
            map.put("group", student.getStudentGroup());
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Student> getAllEntities(Filter filter) {
        return filter.filtrate(studentRepository.findAll());
    }

    @Override
    public void transfer(List<String> IDs) {
        for (String id : IDs) {
            Student student = studentRepository.findById(id).get();
            student.setCourse(student.getCourse() + 1);
            studentRepository.save(student);
        }
    }

    @Override
    public void delete(String id) {
        Student studentToDelete = studentRepository.findById(id).get();
        studentToDelete.getPoints().clear();
        studentToDelete.getSubjects().clear();
        studentToDelete.getAds().clear();
        studentRepository.deleteById(id);
    }

    public long groupCount() {
        List<Student> all = studentRepository.findAll();
        Map<String, Integer> map = new HashMap<>();
        all.stream()
                .map(student -> student.getDirection().getName() + student.getCourse() + student.getStudentGroup())
                .forEach(st -> {
                    map.putIfAbsent(st, 0);
                    Integer put = map.put(st, map.get(st) + 1);
                });
        long count = 0;
        for (String key : map.keySet())
            count += map.get(key);
        return count;
    }

    @Override
    public long count() {
        return studentRepository.count();
    }

    @Override
    public Student find(String id) {
        return studentRepository.findById(id).get();
    }
}

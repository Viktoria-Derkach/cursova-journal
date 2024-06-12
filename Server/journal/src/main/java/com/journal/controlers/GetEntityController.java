package com.journal.controlers;

import com.journal.entities.Student;
import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.factories.ExtractAllServiceFactory;
import com.journal.factories.FilterFactory;
import com.journal.factories.FindByIdFactory;
import com.journal.factories.FindByNameServiceFactory;
import com.journal.services.interfaces.ExtractableAll;
import com.journal.services.interfaces.FindableByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/get/entity")
public class GetEntityController {

    @Autowired
    private ExtractAllServiceFactory extractAllServiceFactory;
    @Autowired
    private FindByNameServiceFactory findByNameServiceFactory;
    @Autowired
    private FindByIdFactory findByIdFactory;

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(
            @RequestParam Map<String, String> filters) {
        System.out.println("log all student extraction");
        ExtractableAll extractionAllService = extractAllServiceFactory.getService("StudentService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        List<Map<String, Object>> response = extractionAllService.getAll(filterFactory.getEntityFilter("student", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers(
            @RequestParam Map<String, String> filters) {
        System.out.println("log all teacher extraction");
        ExtractableAll extractionAllService = extractAllServiceFactory.getService("TeacherService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        List<Map<String, Object>> response = extractionAllService.getAll(filterFactory.getEntityFilter("teacher", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects(
            @RequestParam Map<String, String> filters) {
        ExtractableAll extractionAllService = extractAllServiceFactory.getService("SubjectService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        List<Map<String, Object>> response = extractionAllService.getAll(filterFactory.getEntityFilter("subject", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/subject/groups")
    public ResponseEntity<?> getSubjectGroups(@RequestParam String name) {
        FindableByName<?> service = findByNameServiceFactory.getService("SubjectService");
        Subject subject = (Subject) service.findByName(name);
        List<Student> students = subject.getStudents();
        Set<Map<String, String>> response = students.stream().map(student -> {
            Map<String, String> map = new HashMap<>();
            map.put("direction", student.getDirection().getName());
            map.put("course", String.valueOf(student.getCourse()));
            map.put("group", String.valueOf(student.getStudentGroup()));
            return map;
        }).collect(Collectors.toSet());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/teacher/subject")
    public ResponseEntity<?> getTeacherSubjects(@RequestParam("id") String id) {
        Teacher teacher = (Teacher) findByIdFactory.getService("TeacherService").find(id);
        List<Map<String, Object>> response = teacher.getSubjects().stream().map(subject -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", subject.getName());
            map.put("hours", subject.getHours());
            map.put("teacher", subject.getTeachers().stream().map(Teacher::getName).collect(Collectors.toList()));
            map.put("direction", subject.getDirection().getName());
            map.put("course", subject.getCourse());
            map.put("group", subject.getStudentGroup());
            map.put("chair", subject.getChair().getName());
            return map;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/subject")
    public ResponseEntity<?> getStudentSubjects(@RequestParam String id) {
        Student student = (Student) findByIdFactory.getService("StudentService").find(id);
        List<Map<String, Object>> response = student.getSubjects().stream().map(subject -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", subject.getName());
            map.put("hours", subject.getHours());
            map.put("teacher", subject.getTeachers().stream().map(Teacher::getName).collect(Collectors.toList()));
            map.put("chair", subject.getChair().getName());
            return map;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
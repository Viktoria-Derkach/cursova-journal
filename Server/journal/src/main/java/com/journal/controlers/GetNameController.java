package com.journal.controlers;

import com.journal.entities.Student;
import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.factories.ExtractNameServiceFactory;
import com.journal.factories.FilterFactory;
import com.journal.factories.FindByIdFactory;
import com.journal.services.interfaces.ExtractableNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/get/name")
public class GetNameController {

    @Autowired
    private ExtractNameServiceFactory extractNameServiceFactory;
    @Autowired
    private FindByIdFactory findByIdFactory;

    @GetMapping("/subject/name")
    public ResponseEntity<?> getAllSubjectsName(
            @RequestParam Map<String, String> filters) {
        System.out.println("log getting subjects name");
        ExtractableNames service = extractNameServiceFactory.getService("SubjectService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        Set<?> response = service.getEntitiesName(filterFactory.getEntityFilter("subject", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/teacher/name")
    public ResponseEntity<?> getAllTeachersName(
            @RequestParam Map<String, String> filters) {
        System.out.println("log getting subjects name");
        ExtractableNames service = extractNameServiceFactory.getService("TeacherService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        Set<?> response = service.getEntitiesName(filterFactory.getEntityFilter("teacher", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/directions")
    public ResponseEntity<?> getAllSpecialties(
            @RequestParam Map<String, String> filters) {
        System.out.println("log getting directions");
        ExtractableNames service = extractNameServiceFactory.getService("DirectionService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        Set<?> response = service.getEntitiesName(filterFactory.getEntityFilter("direction", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/faculties")
    public ResponseEntity<?> getAllFaculties() {
        System.out.println("log getting directions");
        ExtractableNames service = extractNameServiceFactory.getService("FacultyService");
        Set<?> response = service.getEntitiesName(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chairs")
    public ResponseEntity<?> getAllChairs(
            @RequestParam Map<String, String> filters) {
        System.out.println("log getting directions");
        ExtractableNames service = extractNameServiceFactory.getService("ChairService");
        FilterFactory filterFactory = FilterFactory.getInstance();
        Set<?> response = service.getEntitiesName(filterFactory.getEntityFilter("chair", filters));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/teacher/subject")
    public ResponseEntity<?> getTeacherSubjectsName(@RequestParam("id") String id) {
        Teacher teacher = (Teacher) findByIdFactory.getService("TeacherService").find(id);
        List<String> subjectsName = teacher.getSubjects().stream().map(Subject::getName).collect(Collectors.toList());
        return new ResponseEntity<>(subjectsName, HttpStatus.OK);
    }

    @GetMapping("/student/subject")
    public ResponseEntity<?> getStudentSubjectNames(@RequestParam("id") String id) {
        Student student = (Student) findByIdFactory.getService("StudentService").find(id);
        List<String> subjectsName = student.getSubjects().stream().map(Subject::getName).collect(Collectors.toList());
        return new ResponseEntity<>(subjectsName, HttpStatus.OK);
    }
}

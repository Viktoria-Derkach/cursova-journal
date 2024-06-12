package com.journal.controlers;

import com.journal.entities.Student;
import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.services.DirectionService;
import com.journal.services.FacultyService;
import com.journal.services.StudentService;
import com.journal.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/get/info")
public class GetInfoController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private DirectionService directionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllInfo() {
        Map<String, Long> map = new HashMap<>();
        map.put("faculty", facultyService.count());
        map.put("direction", directionService.count());
        map.put("group", studentService.groupCount());
        map.put("student", studentService.count());
        map.put("teacher", teacherService.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/teacher")
    public ResponseEntity<?> getTeacherInfo(@RequestParam String id) {
        Teacher teacher = teacherService.find(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", teacher.getName());
        map.put("faculty", teacher.getFaculty().getName());
        map.put("chair", teacher.getChair().getName());
        map.put("subjects", teacher.getSubjects().stream().map(Subject::getName).collect(Collectors.toList()));
        map.put("hours", teacher.getSubjects().stream().mapToInt(Subject::getHours).sum());
        map.put("students", teacher.getSubjects().stream().flatMap(subject -> subject.getStudents().stream()).distinct().count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<?> getStudentInfo(@RequestParam String id) {
        Student student = studentService.find(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", student.getName());
        map.put("faculty", student.getFaculty().getName());
        map.put("direction", student.getDirection().getName());
        map.put("course", student.getCourse());
        map.put("group", student.getStudentGroup());
        map.put("number", student.getNumber());
        map.put("subjects", student.getSubjects().size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

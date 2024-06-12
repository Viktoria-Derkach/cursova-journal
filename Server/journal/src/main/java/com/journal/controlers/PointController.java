package com.journal.controlers;

import com.journal.entities.Point;
import com.journal.entities.Student;
import com.journal.entities.Subject;
import com.journal.entities.Teacher;
import com.journal.factories.FilterFactory;
import com.journal.factories.FindByIdFactory;
import com.journal.factories.FindByNameServiceFactory;
import com.journal.factories.InsertionServiceFactory;
import com.journal.filters.Filter;
import com.journal.services.interfaces.FindableById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private FindByNameServiceFactory findByNameServiceFactory;
    @Autowired
    private FindByIdFactory findByIdFactory;
    @Autowired
    private InsertionServiceFactory insertionServiceFactory;

    @PostMapping("/insert")
    public ResponseEntity<?> insertPoint(
            @RequestBody Map<String, String> request
    ) {
        System.out.println(request);
        Student student = (Student) findByIdFactory.getService("StudentService").find(request.get("studentId"));
        Subject subject = (Subject) findByNameServiceFactory.getService("SubjectService").findByName(request.get("subject"));
        String dateString = request.get("date");
        Point point = new Point(Integer.parseInt(request.get("point")), LocalDate.parse(dateString), student, subject);
        insertionServiceFactory.getService("PointService").InsertEntity(point);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/get/teacher")
    public ResponseEntity<?> getTeacherPoints(
            @RequestParam("id") String id,
            @RequestParam("subject") String subjectName,
            @RequestParam("direction") String direction,
            @RequestParam("course") String course,
            @RequestParam("group") String group
    ) {
        FindableById<?> teacherService = findByIdFactory.getService("TeacherService");
        Teacher teacher = (Teacher) teacherService.find(id);
        Subject subject = teacher.getSubjects().stream()
                .filter(s -> s.getName().equals(subjectName)).collect(Collectors.toList()).get(0);
        Filter studentFilter = FilterFactory.getInstance().getEntityFilter("student", Map.of(
                "direction", direction,
                "course", course,
                "group", group
        ));
        List<Student> students = studentFilter.filtrate(subject.getStudents());
        Set<LocalDate> dates = new LinkedHashSet<>();
        List<Map<String, Object>> studentsList = students.stream().map(student -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", student.getId());
            map.put("name", student.getName());
            map.put("points", student.getPoints().stream().sorted(Comparator.comparing(Point::getPostDate))
                    .filter(point -> point.getSubject().getName().equals(subjectName))
                    .peek(point -> dates.add(point.getPostDate()))
                    .map(point -> Map.of("date", point.getPostDate(), "value", point.getValue())).collect(Collectors.toList()));
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> response = Map.of(
                "date", dates.stream().sorted().collect(Collectors.toList()),
                "studentsArray", studentsList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/student")
    public ResponseEntity<?> getStudentPoints(
            @RequestParam("id") String id,
            @RequestParam("subject") String subjectName
    ) {
        FindableById<?> studentService = findByIdFactory.getService("StudentService");
        Student student = (Student) studentService.find(id);
        Subject subject = student.getSubjects().stream()
                .filter(s -> s.getName().equals(subjectName)).findFirst().get();
        Filter studentFilter = FilterFactory.getInstance().getEntityFilter("student", Map.of(
                "direction", student.getDirection().getName(),
                "course", String.valueOf(student.getCourse()),
                "group", String.valueOf(student.getStudentGroup())
        ));
        List<Student> students = studentFilter.filtrate(subject.getStudents());
        Set<LocalDate> dates = new HashSet<>();
        List<Map<String, Object>> studentsList = new LinkedList<>();
        studentsList.add(Map.of(
                "id", student.getId(),
                "name", student.getName(),
                "points", student.getPoints().stream()
                        .filter(point -> point.getSubject().getName().equals(subjectName))
                        .peek(point -> dates.add(point.getPostDate()))
                        .map(point ->  Map.of("date", point.getPostDate(), "value", point.getValue())).collect(Collectors.toList())
        ));
        studentsList.addAll(students.stream().filter(s -> !s.equals(student)).map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", "");
            map.put("name", "");
            map.put("points", s.getPoints().stream().sorted(Comparator.comparing(Point::getPostDate))
                    .filter(point -> point.getSubject().getName().equals(subjectName))
                    .peek(point -> dates.add(point.getPostDate()))
                    .map(point -> Map.of("date", point.getPostDate(), "value", point.getValue())).collect(Collectors.toList()));
            return map;
        }).collect(Collectors.toList()));
        Map<String, Object> response = Map.of(
                "date", dates.stream().sorted().collect(Collectors.toList()),
                "studentsArray", studentsList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

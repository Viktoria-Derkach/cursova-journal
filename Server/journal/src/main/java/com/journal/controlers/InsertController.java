package com.journal.controlers;

import com.journal.email.EmailSender;
import com.journal.entities.*;
import com.journal.factories.ExtractAllServiceFactory;
import com.journal.factories.FilterFactory;
import com.journal.factories.FindByNameServiceFactory;
import com.journal.factories.InsertionServiceFactory;
import com.journal.filters.Filter;
import com.journal.services.interfaces.FindableByName;
import com.journal.services.interfaces.Insertable;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insert")
public class InsertController {

    private static final String VALID_PW_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_PASSWORD_LENGTH = 8;
    @Autowired
    private InsertionServiceFactory factory;
    @Autowired
    private EmailSender sender;
    @Autowired
    private FindByNameServiceFactory findByNameServiceFactory;
    @Autowired
    private ExtractAllServiceFactory extractAllServiceFactory;

    @PostMapping("/student")
    public boolean insertStudent(@RequestBody List<Student> students) {
        System.out.println("log student insertion");
        Insertable<Student> studentService = factory.getService("StudentService");
        FindableByName<?> facultyService = findByNameServiceFactory.getService("FacultyService");
        FindableByName<?> directionService = findByNameServiceFactory.getService("DirectionService");
        for (Student student : students) {
            System.out.println(student);
            String password = RandomStringUtils.random(DEFAULT_PASSWORD_LENGTH, 0, VALID_PW_CHARS.length(), false,
                    false, VALID_PW_CHARS.toCharArray(), new SecureRandom());
            try {
                sender.sendPassword(student.getEmail(), password);
            } catch (MailException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(password);
            String encryptedPassword = DigestUtils.sha256Hex(password);
            student.setPassword(encryptedPassword);
            student.setFaculty((Faculty) facultyService.findByName(student.getFaculty().getName()));
            student.setDirection((Direction) directionService.findByName(student.getDirection().getName()));
            Map<String, String> filterMap = Map.of(
                    "faculty", student.getFaculty().getName(),
                    "direction", student.getDirection().getName(),
                    "course", String.valueOf(student.getCourse()));
            Filter<?> filter = FilterFactory.getInstance().getEntityFilter("subject", filterMap);
            List<Subject> subjects = extractAllServiceFactory.getService("SubjectService").getAllEntities(filter);
            subjects = subjects.stream()
                    .filter(subject ->
                            subject.getStudentGroup().equals(student.getStudentGroup().toString()) ||
                                    subject.getStudentGroup().equals("All")).collect(Collectors.toList());
            subjects.forEach(student::addSubject);
            studentService.InsertEntity(student);
        }
        return true;
    }

    @PostMapping("/teacher")
    public boolean insertTeacher(@RequestBody List<Teacher> teachers) {
        System.out.println("log teacher insertion");
        Insertable<Teacher> teacherService = factory.getService("TeacherService");
        FindableByName<?> facultyService = findByNameServiceFactory.getService("FacultyService");
        FindableByName<?> chairService = findByNameServiceFactory.getService("ChairService");
        for (Teacher teacher : teachers) {
            String password = RandomStringUtils.random(DEFAULT_PASSWORD_LENGTH, 0, VALID_PW_CHARS.length(), false,
                    false, VALID_PW_CHARS.toCharArray(), new SecureRandom());
            try {
                sender.sendPassword(teacher.getEmail(), password);
            } catch (MailException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(password);
            String encryptedPassword = DigestUtils.sha256Hex(password);
            teacher.setPassword(encryptedPassword);
            teacher.setFaculty((Faculty) facultyService.findByName(teacher.getFaculty().getName()));
            teacher.setChair((Chair) chairService.findByName(teacher.getChair().getName()));
            teacherService.InsertEntity(teacher);
        }
        return true;
    }

    @PostMapping("/subject")
    public boolean insertSubject(@RequestBody List<Map<String, String>> request) {
        System.out.println("log subject insertion");
        Insertable<Subject> authorizationService = factory.getService("SubjectService");
        FindableByName<?> facultyService = findByNameServiceFactory.getService("FacultyService");
        FindableByName<?> directionService = findByNameServiceFactory.getService("DirectionService");
        FindableByName<?> chairService = findByNameServiceFactory.getService("ChairService");
        FindableByName<?> teacherService = findByNameServiceFactory.getService("TeacherService");
        for (Map<String, String> map : request) {
            System.out.println(map);
            Faculty faculty = (Faculty) facultyService.findByName(map.get("faculty"));
            Chair chair = (Chair) chairService.findByName(map.get("chair"));
            Direction direction = (Direction) directionService.findByName(map.get("direction"));
            Subject subject = new Subject(map.get("name"), faculty, chair, direction,
                    Integer.parseInt(map.get("course")), map.get("group"), Integer.parseInt(map.get("hours")));
            subject.addTeacher((Teacher) teacherService.findByName(map.get("teacher")));
            Map<String, String> filterMap = Map.of(
                    "faculty", map.get("faculty"),
                    "direction", map.get("direction"),
                    "course", map.get("course"),
                    "group", map.get("group"));
            Filter<?> studentFilter = FilterFactory.getInstance().getEntityFilter("student", filterMap);
            List<Student> students = extractAllServiceFactory.getService("StudentService").getAllEntities(studentFilter);
            students.forEach(subject::addStudent);
            authorizationService.InsertEntity(subject);
        }
        return true;
    }
}

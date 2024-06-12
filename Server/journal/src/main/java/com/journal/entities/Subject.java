package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Subject {
    @Id
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "uuid2"
    )
    @Column(
            length = 36,
            nullable = false,
            updatable = false
    )
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "chair_id")
    private Chair chair;
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
    private Integer course;
    private String studentGroup;
    private Integer hours;
    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;
    @OneToMany(mappedBy = "subject")
    private List<Point> points;

    public Subject() {
    }

    public Subject(String name,
                   Faculty faculty,
                   Chair chair,
                   Direction direction,
                   Integer course,
                   String studentGroup,
                   Integer hours) {
        this.name = name;
        this.faculty = faculty;
        this.direction = direction;
        this.chair = chair;
        this.course = course;
        this.studentGroup = studentGroup;
        this.hours = hours;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.points = new ArrayList<>();
    }

    public Subject(String id,
                   String name,
                   Faculty faculty,
                   Chair chair,
                   Direction direction,
                   Integer course,
                   String studentGroup,
                   Integer hours) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.chair = chair;
        this.direction = direction;
        this.course = course;
        this.studentGroup = studentGroup;
        this.hours = hours;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.points = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void remove(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void removePoint(Point point) {
        this.points.remove(point);
    }
}

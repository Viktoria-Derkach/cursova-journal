package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Faculty {
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

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
    @OneToMany(mappedBy = "faculty")
    private List<Subject> subjects;
    @OneToMany(mappedBy = "faculty")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "faculty")
    private List<Chair> chairs;
    @OneToMany(mappedBy = "faculty")
    private List<Direction> directions;

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public Faculty(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.teachers = new ArrayList<>();

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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
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

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public void addChair(Chair chair) {
        this.chairs.add(chair);
    }

    public void removeChair(Chair chair) {
        this.chairs.remove(chair);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public void addDirection(Direction direction) {
        this.directions.add(direction);
    }

    public void removeDirection(Direction direction) {
        this.directions.remove(direction);
    }
}

package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Chair {
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
    @OneToMany(mappedBy = "chair")
    private List<Subject> subjects;
    @OneToMany(mappedBy = "chair")
    private List<Teacher> teachers;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Chair() {
    }

    public Chair(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public Chair(String id, String name) {
        this.id = id;
        this.name = name;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}

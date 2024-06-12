package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Direction {
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
    @OneToMany(mappedBy = "direction")
    private List<Student> students;
    @OneToMany(mappedBy = "direction")
    private List<Subject> subjects;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Direction() {
    }

    public Direction(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public Direction(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
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
        this.subjects.remove(student);
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}

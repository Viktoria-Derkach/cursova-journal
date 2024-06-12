package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Ad {
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
    private String label;
    @Column(length = 1000)
    private String text;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher postedBy;
    private LocalDate postDate;
    @ManyToMany
    @JoinTable(
            name = "ad_student",
            joinColumns = @JoinColumn(name = "ad_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Ad() {
    }

    public Ad(String label,
              String text,
              LocalDate postDate) {
        this.label = label;
        this.text = text;
        this.postDate = postDate;
        this.students = new ArrayList<>();
    }

    public Ad(String label, String text, Teacher postedBy, LocalDate postDate) {
        this.label = label;
        this.text = text;
        this.postedBy = postedBy;
        this.postDate = postDate;
        this.students = new ArrayList<>();
    }

    public Ad(String id,
              String label,
              String text,
              Teacher postedBy,
              LocalDate postDate) {
        this.id = id;
        this.label = label;
        this.text = text;
        this.postedBy = postedBy;
        this.postDate = postDate;
        this.students = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Teacher getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Teacher postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
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
}

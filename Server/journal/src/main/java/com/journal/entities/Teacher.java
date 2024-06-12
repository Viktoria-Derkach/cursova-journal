package com.journal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Teacher {
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
    private String email;
    private String password;
    private String name;
    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "chair_id", nullable = false)
    private Chair chair;
    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @JsonIgnore
    private List<Subject> subjects;
    @OneToMany(mappedBy = "postedBy", orphanRemoval = true)
    private List<Ad> ads;

    public Teacher() {
    }

    public Teacher(String email,
                   String password,
                   String name,
                   Faculty faculty,
                   Chair chair) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
        this.chair = chair;
        this.subjects = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public Teacher(String email,
                   String name,
                   Faculty faculty,
                   Chair chair) {
        this.email = email;
        this.name = name;
        this.faculty = faculty;
        this.chair = chair;
        this.subjects = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public Teacher(String id,
                   String email,
                   String password,
                   String name,
                   Faculty faculty,
                   Chair chair) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
        this.chair = chair;
        this.subjects = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subjects) {
        this.subjects.remove(subjects);
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public void addAd(Ad ad) {
        this.ads.add(ad);
    }

    public void removeAd(Ad ad) {
        this.ads.remove(ad);
    }
}

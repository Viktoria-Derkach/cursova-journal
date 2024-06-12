package com.journal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Student {
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
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
    private Integer course;
    private Integer studentGroup;
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<Point> points;
    @ManyToMany
    @JoinTable(
            name = "ad_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "ad_id")
    )
    private List<Ad> ads;

    public Student() {
        this.subjects = new ArrayList<>();
        this.points = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public Student(String email,
                   String password,
                   String name,
                   Integer number,
                   Faculty faculty,
                   Direction direction,
                   Integer course,
                   Integer studentGroup) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.number = number;
        this.faculty = faculty;
        this.direction = direction;
        this.course = course;
        this.studentGroup = studentGroup;
        this.subjects = new ArrayList<>();
        this.points = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public Student(String email,
                   String name,
                   Integer number,
                   Faculty faculty,
                   Direction direction,
                   Integer course,
                   Integer studentGroup) {
        this.email = email;
        this.name = name;
        this.number = number;
        this.faculty = faculty;
        this.direction = direction;
        this.course = course;
        this.studentGroup = studentGroup;
        this.subjects = new ArrayList<>();
        this.points = new ArrayList<>();
        this.ads = new ArrayList<>();
    }

    public Student(String id,
                   String email,
                   String password,
                   String name,
                   Integer number,
                   Faculty faculty,
                   Direction direction,
                   Integer course,
                   Integer studentGroup) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.number = number;
        this.faculty = faculty;
        this.direction = direction;
        this.course = course;
        this.studentGroup = studentGroup;
        this.subjects = new ArrayList<>();
        this.points = new ArrayList<>();
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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

    public Integer getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Integer studentGroup) {
        this.studentGroup = studentGroup;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }
}

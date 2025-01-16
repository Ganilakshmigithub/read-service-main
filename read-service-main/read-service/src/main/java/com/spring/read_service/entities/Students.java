package com.spring.read_service.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String gender;
    private String dob;
    private String course;
    private int courseStartYear;
    private int courseEndYear;
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="student_id")
    private List<Subject> subjects=new ArrayList<>();
    public Students() {
    }
    public Students(String name, int age, String gender, String dob, String course, int courseStartYear,
                    int courseEndYear, List<Subject> subjects) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dob = dob;
        this.course = course;
        this.courseStartYear = courseStartYear;
        this.courseEndYear = courseEndYear;
        this.subjects = subjects;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getCourseStartYear() {
        return courseStartYear;
    }
    public void setCourseStartYear(int courseStartYear) {
        this.courseStartYear = courseStartYear;
    }
    public int getCourseEndYear() {
        return courseEndYear;
    }
    public void setCourseEndYear(int courseEndYear) {
        this.courseEndYear = courseEndYear;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }





}


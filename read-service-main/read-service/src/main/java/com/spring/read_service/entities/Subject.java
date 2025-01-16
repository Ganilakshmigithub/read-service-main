package com.spring.read_service.entities;

import jakarta.persistence.*;
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subId;
    private String name;
    private int marks;



    public Subject() {
    }
    public Subject(int subId, String name, int marks) {
        this.subId = subId;
        this.name = name;
        this.marks = marks;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public int getSubId() {
        return subId;
    }
    public void setSubId(int subId) {
        this.subId = subId;
    }
}


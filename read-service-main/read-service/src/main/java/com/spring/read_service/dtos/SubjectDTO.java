package com.spring.read_service.dtos;

public class SubjectDTO {
    private int subId;
    private String name;
    private int marks;
    // Constructor
    public SubjectDTO(int subId, String name, int marks) {
        this.subId = subId;
        this.name = name;
        this.marks = marks;
    }
    // Getters and Setters
    public int getSubId() {
        return subId;
    }
    public void setSubId(int subId) {
        this.subId = subId;
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
}


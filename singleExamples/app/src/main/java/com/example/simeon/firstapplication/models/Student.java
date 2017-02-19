package com.example.simeon.firstapplication.models;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String school;

    public Student(String name, String school) {
        this.setName(name);
        this.setSchool(school);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

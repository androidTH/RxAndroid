package com.example.jjr.rxandroid;

import java.io.Serializable;

/**
 * Created by jjr on 2016/1/16.
 */
public class Student implements Serializable {
    private String name;
    private Course mCourse;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name,Course course) {
        this.name = name;
        this.mCourse = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getmCourse() {
        return mCourse;
    }

    public void setmCourse(Course mCourse) {
        this.mCourse = mCourse;
    }
}

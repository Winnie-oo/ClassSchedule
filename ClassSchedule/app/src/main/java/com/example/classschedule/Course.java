package com.example.classschedule;


import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private int course_id;
    private String course_name;
    private String teacher;
    private String week;

    public Course(int id,int course_id,String course_name, String teacher, String week) {
        this.id = id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.teacher = teacher;
        this.week = week;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() { return course_id; }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourseName(String course_name) {
        this.course_name = course_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

}

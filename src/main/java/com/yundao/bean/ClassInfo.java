package com.yundao.bean;

import java.util.List;

public class ClassInfo extends BaseInfo{

    private String classId;//班课编号
    private String className;//班课名称
    private String classCourse;
    private String classTeacher;
    private String classTime;
    private byte classStatus;
    private int classStudent;
    private String classCollege;
    private String classTerm;
    private String classDetail;

    private UserInfo teacher;
    private TimeInfo time;
    private List<UserClass> userClasses;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCourse() {
        return classCourse;
    }

    public void setClassCourse(String classCourse) {
        this.classCourse = classCourse;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public byte getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(byte classStatus) {
        this.classStatus = classStatus;
    }

    public int getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(int classStudent) {
        this.classStudent = classStudent;
    }

    public String getClassCollege() {
        return classCollege;
    }

    public void setClassCollege(String classCollege) {
        this.classCollege = classCollege;
    }

    public String getClassTerm() {
        return classTerm;
    }

    public void setClassTerm(String classTerm) {
        this.classTerm = classTerm;
    }

    public String getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(String classDetail) {
        this.classDetail = classDetail;
    }

    public UserInfo getTeacher() {
        return teacher;
    }

    public void setTeacher(UserInfo teacher) {
        this.teacher = teacher;
    }

    public TimeInfo getTime() {
        return time;
    }

    public void setTime(TimeInfo time) {
        this.time = time;
    }

    public List<UserClass> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(List<UserClass> userClasses) {
        this.userClasses = userClasses;
    }
}

package com.db.education.Time.messages;

import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Time.entity.Time;

import java.io.Serializable;
import java.util.List;

public class SemesterCourseTimeReponse implements Serializable {
    private Time time;
    private SemesterCourse semesterCourse;

    public SemesterCourseTimeReponse() {
    }

    public SemesterCourseTimeReponse(Time time, SemesterCourse semesterCourse) {
        this.time = time;
        this.semesterCourse = semesterCourse;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public SemesterCourse getSemesterCourse() {
        return semesterCourse;
    }

    public void setSemesterCourse(SemesterCourse semesterCourse) {
        this.semesterCourse = semesterCourse;
    }
}

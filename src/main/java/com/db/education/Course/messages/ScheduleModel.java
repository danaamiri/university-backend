package com.db.education.Course.messages;

import com.db.education.Course.entity.Course;
import com.db.education.Course.entity.CourseTime;
import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Semester.entity.Semester;
import com.db.education.Time.messages.SemesterCourseTimeReponse;

import java.io.Serializable;

public class ScheduleModel implements Serializable {
    private String weekday;
    private SemesterCourse firstTime;
    private SemesterCourse secondTime;
    private SemesterCourse thirdTime;
    private SemesterCourse forthTime;

    public ScheduleModel() {
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public SemesterCourse getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(SemesterCourse firstTime) {
        this.firstTime = firstTime;
    }

    public SemesterCourse getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(SemesterCourse secondTime) {
        this.secondTime = secondTime;
    }

    public SemesterCourse getThirdTime() {
        return thirdTime;
    }

    public void setThirdTime(SemesterCourse thirdTime) {
        this.thirdTime = thirdTime;
    }

    public SemesterCourse getForthTime() {
        return forthTime;
    }

    public void setForthTime(SemesterCourse forthTime) {
        this.forthTime = forthTime;
    }
}

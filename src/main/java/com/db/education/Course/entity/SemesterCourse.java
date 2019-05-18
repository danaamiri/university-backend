package com.db.education.Course.entity;

import com.db.education.Semester.entity.Semester;
import com.db.education.Time.entity.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tb_semester_course")
public class SemesterCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor_name")
    private String instructorName;

    @Column(name = "final_exam_time")
    private Timestamp finalExamTime;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Semester semester;

    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToMany
    @JoinColumn(name = "semester_course_id")
    private List<Time> time;

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

    public SemesterCourse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Timestamp getFinalExamTime() {
        return finalExamTime;
    }

    public void setFinalExamTime(Timestamp finalExamTime) {
        this.finalExamTime = finalExamTime;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

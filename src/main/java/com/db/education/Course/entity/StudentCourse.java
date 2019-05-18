package com.db.education.Course.entity;

import com.db.education.Semester.entity.Semester;
import com.db.education.User.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "tb_student_course")
public class StudentCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    @Min(value = 0)
    @Max(value = 20)
    private Integer mark;

    @ManyToOne
    @JoinColumn
    private SemesterCourse semesterCourse;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private User user;

    public StudentCourse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public SemesterCourse getSemesterCourse() {
        return semesterCourse;
    }

    public void setSemesterCourse(SemesterCourse semesterCourse) {
        this.semesterCourse = semesterCourse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

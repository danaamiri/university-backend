package com.db.education.Course.entity;

import com.db.education.Time.entity.Time;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_course_time")
public class CourseTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn
    private Set<Time> time;

    @ManyToOne
    @JoinColumn
    private SemesterCourse semesterCourse;

    public CourseTime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Time> getTime() {
        return time;
    }

    public void setTime(Set<Time> time) {
        this.time = time;
    }

    public SemesterCourse getSemesterCourse() {
        return semesterCourse;
    }

    public void setSemesterCourse(SemesterCourse semesterCourse) {
        this.semesterCourse = semesterCourse;
    }
}

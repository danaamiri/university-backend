package com.db.education.Semester.entity;

import com.db.education.Time.entity.Time;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_semesters")
public class Semester implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private String year;

    @Column(name = "year_half")
    @Max(2)
    @Min(1)
    private int yearHalf;

    @Column(name = "start_semester")
    private Timestamp startSemester;

    @Column(name = "end_semester")
    private Timestamp endSemester;

    public Semester() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Timestamp getStartSemester() {
        return startSemester;
    }

    public void setStartSemester(Timestamp startSemester) {
        this.startSemester = startSemester;
    }

    public Timestamp getEndSemester() {
        return endSemester;
    }

    public void setEndSemester(Timestamp endSemester) {
        this.endSemester = endSemester;
    }

    public int getYearHalf() {
        return yearHalf;
    }

    public void setYearHalf(int yearHalf) {
        this.yearHalf = yearHalf;
    }
}

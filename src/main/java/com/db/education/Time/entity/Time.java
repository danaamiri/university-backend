package com.db.education.Time.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_times")
public class Time implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private java.sql.Time startTime;

    @Column(name = "end_time")
    private java.sql.Time endTime;

    @Column(name = "day")
    @Max(value = 7)
    @Min(value = 1)
    private Integer day;

    public Time() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Time getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Time  startTime) {
        this.startTime = startTime;
    }

    public java.sql.Time  getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Time  endTime) {
        this.endTime = endTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}

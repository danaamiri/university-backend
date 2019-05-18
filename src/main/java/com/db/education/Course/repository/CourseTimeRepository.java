package com.db.education.Course.repository;

import com.db.education.Course.entity.CourseTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTimeRepository extends JpaRepository<CourseTime, Long> {
    public List<CourseTime> findBySemesterCourse_Id(Long scId);
}

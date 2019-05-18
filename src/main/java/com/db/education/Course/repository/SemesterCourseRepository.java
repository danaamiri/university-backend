package com.db.education.Course.repository;

import com.db.education.Course.entity.SemesterCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterCourseRepository extends JpaRepository<SemesterCourse, Long> {
    public List<SemesterCourse> findBySemester_id(Long semesterId);
}

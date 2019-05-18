package com.db.education.Course.repository;

import com.db.education.Course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByDepartment_Id(Long deptId, Pageable pageable);
}

package com.db.education.Course.repository;

import com.db.education.Course.entity.SemesterCourse;
import com.db.education.Course.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    public List<StudentCourse> findAllByUser_Id(Long userId);
    public Optional<StudentCourse> findByUser_idAndSemesterCourse_Id(Long userId, Long semesterCourseId);
}

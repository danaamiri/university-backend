package com.db.education.Semester.repository;

import com.db.education.Semester.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Query(value = "select id from tb_semesters where start_semester < current_timestamp and end_semester > current_timestamp",
    nativeQuery = true)
    Long getCurrentSemester();
}

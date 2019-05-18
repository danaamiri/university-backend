package com.db.education.Time.repository;

import com.db.education.Time.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}

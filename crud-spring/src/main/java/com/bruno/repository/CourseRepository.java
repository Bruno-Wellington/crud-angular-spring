package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}

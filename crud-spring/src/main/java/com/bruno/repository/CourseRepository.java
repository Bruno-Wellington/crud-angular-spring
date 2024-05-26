package com.bruno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}

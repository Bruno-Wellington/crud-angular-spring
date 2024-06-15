package com.bruno.dto.mapper;

import org.springframework.stereotype.Component;

import com.bruno.dto.CourseDTO;
import com.bruno.enums.Category;
import com.bruno.enums.Status;
import com.bruno.model.Course;

@Component
public class CourseMapper {
    
    public CourseDTO toDTO(Course course) {
        if (course == null){
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), "Front-End");
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null){
            return null;
        }

        Course course = new Course();

        if (courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONT_END);
        course.setStatus(Status.ATIVO);
        return course;
    }
}

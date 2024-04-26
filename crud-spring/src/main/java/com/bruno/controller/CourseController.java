package com.bruno.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.model.Course;
import com.bruno.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor //Anotação do lombok que cria os construtores
public class CourseController {
    
    private final CourseRepository courseRepository;

    /*Construtor ja criado pela anotação do LOMBOK @AllArgsConstructor
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }*/

    //@RequestMapping(method = RequestMethod.GET) ou
    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }
}

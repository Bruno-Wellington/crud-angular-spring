package com.bruno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.model.Course;
import com.bruno.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    private final CourseService courseService;
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //@RequestMapping(method = RequestMethod.GET) ou
    @GetMapping
    public @ResponseBody List<Course> list(){
        return courseService.list();
    }

    @GetMapping("/{id}") //Busca na Url o id
    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id);
    }

    //@RequestMapping(method = RequestMethod.POST) ou
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course){
        return courseService.create(course);
    }

    @PutMapping("/{id}")//Busca na Url o id
    public Course update(@PathVariable  @NotNull @Positive Long id, 
        @RequestBody @Valid Course course){
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")//Busca na Url o id
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);    
    }
}

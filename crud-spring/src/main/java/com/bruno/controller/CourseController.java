package com.bruno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.model.Course;
import com.bruno.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
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

    @GetMapping("/{id}") //Busca na Url o id
    public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST) ou
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course){
        //System.out.println(course.getName());
        return courseRepository.save(course);
    }
    //ou
    /* public ResponseEntity<Course> create(@RequestBody Course course){
        //System.out.println(course.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(courseRepository.save(course));
    }*/

    @PutMapping("/{id}")//Busca na Url o id
    public ResponseEntity<Course> update(@PathVariable  @NotNull @Positive Long id, @RequestBody @Valid Course course){
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                Course updated = courseRepository.save(recordFound);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());//tratamento caso nao ache o id
    }

    @DeleteMapping("/{id}")//Busca na Url o id
    public ResponseEntity<Void> delete(@PathVariable  @NotNull @Positive Long id){
        return courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build(); // o void antes do build faz o casting da resposta do responseEntity para vazio.
            })
            .orElse(ResponseEntity.notFound().build());//tratamento caso nao ache o id
    }
}

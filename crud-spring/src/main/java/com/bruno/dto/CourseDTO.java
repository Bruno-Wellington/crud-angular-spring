package com.bruno.dto;

import java.util.List;
import org.hibernate.validator.constraints.Length;

import com.bruno.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotBlank @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category,
    List<Lesson> lessons) {

}

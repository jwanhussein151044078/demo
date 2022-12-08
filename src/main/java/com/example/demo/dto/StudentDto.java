package com.example.demo.dto;

import com.example.demo.model.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class StudentDto {
    private Long   id ;
    private String name;
    private String surName;
    private String email  ;
    @JsonIgnoreProperties("enrolledStudents")
    private Set<Course> enrolledCources ;
}

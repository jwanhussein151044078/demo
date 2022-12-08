package com.example.demo.dto;

import com.example.demo.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String code;
    @JsonIgnoreProperties("enrolledCources")
    private Set<Student> enrolledStudents;
}

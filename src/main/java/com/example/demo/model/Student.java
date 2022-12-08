package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName =  "student_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name ;
    private String surName;
    private String email;

    @ManyToMany(mappedBy = "enrolledStudents",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Course> enrolledCources ;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

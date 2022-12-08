package com.example.demo.model;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Course {
    @javax.persistence.Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName =  "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long   id ;
    private String name;
    private String code;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "students_courses",
            joinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Student> enrolledStudents ;

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

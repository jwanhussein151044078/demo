package com.example.demo.controller;

import com.example.demo.dto.CourseDto;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok().body(courseService.getCourseById(id));
    }
    @PostMapping("/new")
    public ResponseEntity<CourseDto> postCourse(@RequestBody CourseDto courseDto){
        return ResponseEntity.created(URI.create("/api/courses/new")).body(courseService.postCourse(courseDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourseByID(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok().body("the Course has been deleted !!");
    }

    @PostMapping("/{courseId}/enroll/student/{studentId}")
    public ResponseEntity<CourseDto> enrollStudentInCourse(@PathVariable Long courseId,
                                                           @PathVariable Long studentId){
        return ResponseEntity.created(URI.create("/api/courses")).body(courseService.enrollStudentInCourse(courseId,studentId));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CourseDto> putcourse(@PathVariable Long id,
                                               @RequestBody CourseDto courseDto){
        return ResponseEntity.ok().body(courseService.putCourse(id,courseDto));
    }

}

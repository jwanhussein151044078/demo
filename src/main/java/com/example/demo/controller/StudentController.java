package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/list")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<StudentDto> postStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.created(URI.create("/api/students/new")).body(studentService.postStudent(studentDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable Long id,@RequestBody StudentDto student){
        return ResponseEntity.ok().body(studentService.editStudent(id ,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().body("Student has been deleted !!");
    }

}

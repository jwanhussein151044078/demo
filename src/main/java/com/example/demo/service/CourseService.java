package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.mapper.CourseMapper;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotExistsException;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    public List<CourseDto> getAllCourses() {
        return CourseMapper.INSTANCE.listOfCourseToListOfCourseDto(courseRepository.findAll());
    }

    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new NotExistsException("Course with ID {" + id + "} does not exists")
                );
        return CourseMapper.INSTANCE.courseToCourseDto(course);
    }

    public CourseDto postCourse(CourseDto courseDto) {
        if(courseDto.getCode()!= null){
            Optional<Course> optionalCourse = courseRepository.findByCode(courseDto.getCode());
            if(optionalCourse.isPresent()){
                throw new AlreadyExistsException("Course with the Code {"+courseDto.getCode()+"} is already exists");
            }else{
                return CourseMapper.INSTANCE.courseToCourseDto(courseRepository.save(CourseMapper.INSTANCE.courseDtoToCourse(courseDto)));
            }
        }else{
            throw new RuntimeException("Can not save a Course without a Code !!");
        }
    }

    public void deleteCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new NotExistsException("Course with ID {" + id + "} does not exists")
                );
        courseRepository.delete(course);
    }

    public CourseDto enrollStudentInCourse(Long courseId, Long studentId) {
        Course course   = courseRepository.findById(courseId)
                .orElseThrow(()->
                        new NotExistsException("Course with ID {"+courseId+"} does not exists")
                );
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->
                        new NotExistsException("Student with ID {"+studentId+"} does not exists")
                );

        if(course.getEnrolledStudents() == null){
            course.setEnrolledStudents(new HashSet<Student>());
        }
        course.getEnrolledStudents().add(student);
        return CourseMapper.INSTANCE.courseToCourseDto(courseRepository.save(course));
    }

    public CourseDto putCourse(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->
                        new NotExistsException("Course with ID {"+id+"} does not exists")
                );
        if(courseDto.getCode() != null && courseDto.getCode().length() > 0 && !courseRepository.existsByCode(courseDto.getCode())){
            course.setCode(courseDto.getCode());
        }
        if(courseDto.getName() != null && courseDto.getName().length() > 0){
            course.setName(courseDto.getName());
        }
        return CourseMapper.INSTANCE.courseToCourseDto(courseRepository.save(course));
    }
}
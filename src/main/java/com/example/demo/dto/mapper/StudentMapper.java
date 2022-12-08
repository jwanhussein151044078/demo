package com.example.demo.dto.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    Student    studentDtoToStudent(StudentDto studentDTO);
    StudentDto studentToStudentDto(Student student);
    List<StudentDto> listOfStudentToStudentDto(List<Student> studentList);
}

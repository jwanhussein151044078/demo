package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.mapper.StudentMapper;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotExistsException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDto> getAllStudents() {
        return StudentMapper.INSTANCE.listOfStudentToStudentDto(studentRepository.findAll());
    }


    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new NotExistsException("Student with id {"+id+"} does not exists"));
        return StudentMapper.INSTANCE.studentToStudentDto(student);
    }

    public StudentDto postStudent(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if(!optionalStudent.isPresent()){
            return StudentMapper.INSTANCE.studentToStudentDto(studentRepository.save(StudentMapper.INSTANCE.studentDtoToStudent(studentDto)));
        }else{
            throw new AlreadyExistsException("Student with the email {"+studentDto.getEmail()+"} is already exists");
        }
    }

    public StudentDto editStudent(Long id, StudentDto studentDto) {
        Student s = studentRepository.findById(id)
                .orElseThrow(()-> new NotExistsException("Student with id {"+id+"} does not exists"));
        if(studentDto.getName() != null){
            s.setName(studentDto.getName());
        }
        if(studentDto.getSurName() != null){
            s.setSurName(studentDto.getSurName());
        }
        if(studentDto.getEmail() != null){
            if(studentRepository.existsByEmail(studentDto.getEmail())){
                throw new AlreadyExistsException("Student with the email {"+studentDto.getEmail()+"} is already exists");
            }else{
                s.setEmail(studentDto.getEmail());
            }
        }
        return StudentMapper.INSTANCE.studentToStudentDto(studentRepository.save(s));
    }

    public void deleteStudentById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(()-> new NotExistsException("Student with id {"+id+"} does not exists"));
        studentRepository.delete(s);
    }
}

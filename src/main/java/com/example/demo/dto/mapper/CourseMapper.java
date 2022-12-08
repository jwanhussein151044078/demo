package com.example.demo.dto.mapper;

import com.example.demo.dto.CourseDto;
import com.example.demo.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    Course courseDtoToCourse(CourseDto courseDTO);
    CourseDto courseToCourseDto(Course course);
    List<CourseDto> listOfCourseToListOfCourseDto(List<Course> courseList);
}

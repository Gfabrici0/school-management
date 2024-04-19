package com.example.demo.domain.DTO.courser;

import com.example.demo.domain.DTO.student.DataStudent;
import com.example.demo.domain.entity.Course;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record DataCourseWithStudents(
    UUID id,
    String name,
    String description,
    List<DataStudent> students
) {
  public DataCourseWithStudents(Course course) {
    this(
        course.getId(),
        course.getName(),
        course.getDescription(),
        course.getCourseStudents().stream().map(
            students -> new DataStudent(students.getStudent())
        ).collect(Collectors.toList())
    );
  }
}

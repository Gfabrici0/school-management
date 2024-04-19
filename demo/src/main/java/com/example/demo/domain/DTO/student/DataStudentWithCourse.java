package com.example.demo.domain.DTO.student;

import com.example.demo.domain.DTO.courser.DataCourse;
import com.example.demo.domain.entity.Student;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record DataStudentWithCourse(
    UUID id,
    String name,
    String registration,
    List<DataCourse> courses
) {
  public DataStudentWithCourse(Student dataStudent) {
    this(
        dataStudent.getId(),
        dataStudent.getName(),
        dataStudent.getRegistration(),
        dataStudent.getStudentCourses().stream().map(
            courses -> new DataCourse(courses.getCourse())
        ).collect(Collectors.toList())
    );
  }
}

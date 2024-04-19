package com.example.demo.domain.DTO.studentCourse;

import com.example.demo.domain.DTO.courser.DataCourse;
import com.example.demo.domain.DTO.student.DataStudent;
import com.example.demo.domain.entity.StudentCourse;

import java.util.UUID;

public record DataStudentCourse(
  UUID id,
  DataStudent student,
  DataCourse course
) {
  public DataStudentCourse(StudentCourse studentCourse) {
    this(
        studentCourse.getId(),
        new DataStudent(studentCourse.getStudent()),
        new DataCourse(studentCourse.getCourse())
    );
  }
}

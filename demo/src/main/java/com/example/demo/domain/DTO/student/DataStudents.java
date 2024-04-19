package com.example.demo.domain.DTO.student;

import com.example.demo.domain.entity.Student;

import java.util.UUID;

public record DataStudents(
    UUID id,
    String name,
    String registration
) {
  public DataStudents(Student student) {
    this(
        student.getId(),
        student.getName(),
        student.getRegistration()
    );
  }

}

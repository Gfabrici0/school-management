package com.example.demo.domain.DTO.student;

import com.example.demo.domain.entity.Student;

import java.util.UUID;

public record DataStudent(
    UUID id,
    String name,
    String registration
) {
  public DataStudent(Student student) {
    this(
      student.getId(),
      student.getName(),
      student.getRegistration()
    );
  }
}

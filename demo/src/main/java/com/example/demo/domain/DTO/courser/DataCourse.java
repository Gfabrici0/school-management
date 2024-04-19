package com.example.demo.domain.DTO.courser;

import com.example.demo.domain.entity.Course;

import java.util.UUID;

public record DataCourse(
    UUID id,
    String name,
    String description
) {
  public DataCourse(Course course) {
    this(
        course.getId(), course.getName(), course.getDescription()
    );
  }
}

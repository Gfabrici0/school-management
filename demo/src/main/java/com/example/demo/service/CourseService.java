package com.example.demo.service;

import com.example.demo.domain.DTO.courser.*;
import com.example.demo.domain.entity.Course;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {
  @Autowired
  private CourseRepository courseRepository;

  public Course registerCourse(DataRegisterCourse dataRegisterCourse) {
    return courseRepository.save(new Course(dataRegisterCourse));
  }

  public Page<DataCourses> getAllCourses(Pageable pageable) {
    return courseRepository.findAll(pageable).map(DataCourses::new);
  }

  public DataCourseWithStudents getCourseById(UUID id) {
    return courseRepository.findById(id).map(DataCourseWithStudents::new)
        .orElseThrow(() -> new CourseNotFoundException("Course not found"));
  }

  public void updateCourse(UUID id, DataUpdateCourse dataUpdateCourse) {
    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    course.updateCourse(dataUpdateCourse);
    courseRepository.save(course);
  }

  public void patchCourse(UUID id, DataUpdateCourse dataUpdateCourse) {
    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    course.patchCourse(dataUpdateCourse);
    courseRepository.save(course);
  }

  public void deleteCourse(UUID id) {
    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException("Course not found"));

    courseRepository.deleteById(course.getId());
  }

}

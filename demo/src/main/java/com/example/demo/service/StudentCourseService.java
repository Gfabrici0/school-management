package com.example.demo.service;

import com.example.demo.domain.DTO.studentCourse.DataRegisterStudentCourse;
import com.example.demo.domain.entity.Course;
import com.example.demo.domain.entity.Student;
import com.example.demo.domain.entity.StudentCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentCourseService {

  @Autowired
  private StudentCourseRepository studentCourseRepository;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private CourseRepository courseRepository;

  public StudentCourse registerStudentCourse(DataRegisterStudentCourse dataRegisterStudentCourse) {
    Student student = studentRepository.findById(dataRegisterStudentCourse.studentId())
        .orElseThrow(() -> new RuntimeException("Student not found"));
    Course course = courseRepository.findById(dataRegisterStudentCourse.courseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

    return studentCourseRepository.save(new StudentCourse(student, course));
  }

  public void deleteStudentCourse(UUID id) {
    StudentCourse student = studentCourseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student or Course not found"));

    studentCourseRepository.deleteById(student.getId());
  }
}

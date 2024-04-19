package com.example.demo.controller;

import com.example.demo.domain.DTO.courser.DataCourse;
import com.example.demo.domain.DTO.courser.DataCourses;
import com.example.demo.domain.DTO.courser.DataRegisterCourse;
import com.example.demo.domain.DTO.courser.DataUpdateCourse;
import com.example.demo.domain.DTO.studentCourse.DataRegisterStudentCourse;
import com.example.demo.domain.DTO.studentCourse.DataStudentCourse;
import com.example.demo.domain.entity.Course;
import com.example.demo.domain.entity.StudentCourse;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("student-course")
@Controller
public class StudentCourseController {

  @Autowired
  private StudentCourseService studentCourseService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataStudentCourse> registerStudentCourse(@RequestBody @Valid DataRegisterStudentCourse dataRegisterStudentCourse) {
    StudentCourse createdStudentCourse = studentCourseService.registerStudentCourse(dataRegisterStudentCourse);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdStudentCourse.getId())
        .toUri();

    return ResponseEntity.created(location).body(new DataStudentCourse(createdStudentCourse));
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteStudentCourseById(@PathVariable UUID id) {
    studentCourseService.deleteStudentCourse(id);
    return ResponseEntity.noContent().build();
  }

}

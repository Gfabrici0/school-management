package com.example.demo.controller;

import com.example.demo.domain.DTO.courser.*;
import com.example.demo.domain.DTO.student.DataUpdateStudent;
import com.example.demo.domain.entity.Course;
import com.example.demo.service.CourseService;
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
@RequestMapping("course")
@Controller
public class CourseController {

  @Autowired
  private CourseService courseService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataCourse> registerCourse(@RequestBody @Valid DataRegisterCourse dataRegisterCourse) {
    Course createdCourse = courseService.registerCourse(dataRegisterCourse);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdCourse.getId())
        .toUri();

    return ResponseEntity.created(location).body(new DataCourse(createdCourse));
  }

  @GetMapping
  public ResponseEntity<Page<DataCourses>> getAllCourses(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
    Page<DataCourses> result = courseService.getAllCourses(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataCourseWithStudents> getCourseById(@PathVariable UUID id) {
    DataCourseWithStudents result = courseService.getCourseById(id);
    return ResponseEntity.ok().body(result);
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<String> updateCourse(@PathVariable UUID id, @RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
    courseService.updateCourse(id, dataUpdateCourse);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @PatchMapping("/{id}")
  public ResponseEntity<String> patchCourse(@PathVariable UUID id, @RequestBody @Valid DataUpdateCourse dataUpdateStudent) {
    courseService.patchCourse(id, dataUpdateStudent);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCourseById(@PathVariable UUID id) {
    courseService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }

}

package com.example.demo.controller;

import com.example.demo.domain.DTO.student.*;
import com.example.demo.domain.entity.Student;
import com.example.demo.service.StudentService;
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
@RequestMapping("student")
@Controller
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataStudent> registerStudent(@RequestBody @Valid DataRegisterStudent dataRegisterStudent) {
    Student createdStudent = studentService.registerStudent(dataRegisterStudent);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdStudent.getId())
        .toUri();

    return ResponseEntity.created(location).body(new DataStudent(createdStudent));
  }

  @GetMapping
  public ResponseEntity<Page<DataStudent>> getAllStudents(@PageableDefault(size = 10, sort = {"registration"}) Pageable pageable) {
    Page<DataStudent> result = studentService.getAllStudents(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataStudentWithCourse> getStudentById(@PathVariable UUID id) {
    DataStudentWithCourse result = studentService.getStudentById(id);
   return ResponseEntity.ok().body(result);
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<String> updateStudent(@PathVariable UUID id,@RequestBody @Valid DataUpdateStudent dataUpdateStudent) {
    studentService.updateStudent(id, dataUpdateStudent);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @PatchMapping("/{id}")
  public ResponseEntity<String> patchStudent(@PathVariable UUID id, @RequestBody @Valid DataUpdateStudent dataUpdateStudent) {
    studentService.patchStudent(id, dataUpdateStudent);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

}

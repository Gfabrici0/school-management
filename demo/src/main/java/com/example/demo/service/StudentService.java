package com.example.demo.service;

import com.example.demo.domain.DTO.student.*;
import com.example.demo.domain.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public Student registerStudent(DataRegisterStudent dataRegisterStudent) {
    return studentRepository.save(new Student(dataRegisterStudent));
  }

  public Page<DataStudent> getAllStudents(Pageable pageable) {
    return studentRepository.findAll(pageable).map(DataStudent::new);
  }

  public DataStudentWithCourse getStudentById(UUID id) {
    return studentRepository.findById(id).map(DataStudentWithCourse::new)
        .orElseThrow(() -> new StudentNotFoundException("Student not found"));
  }

  public void updateStudent(UUID id, DataUpdateStudent dataUpdateStudent) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    student.updateStudent(dataUpdateStudent);
    studentRepository.save(student);
  }

  public void patchStudent(UUID id, DataUpdateStudent dataUpdateStudent) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    student.patchStudent(dataUpdateStudent);
    studentRepository.save(student);
  }

  public void deleteStudent(UUID id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException("Student not found"));

    studentRepository.deleteById(student.getId());
  }

}

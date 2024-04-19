package com.example.demo.domain.entity;

import com.example.demo.domain.DTO.student.DataRegisterStudent;
import com.example.demo.domain.DTO.student.DataUpdateStudent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Student {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  public UUID id;

  @Column(name = "name", nullable = false)
  public String name;

  @Column(name = "registration", nullable = false)
  public String registration;

  @OneToMany(mappedBy = "student")
  private List<StudentCourse> studentCourses = new ArrayList<>();

  public Student(DataRegisterStudent dataRegisterStudent) {
    this.name = dataRegisterStudent.name();
    this.registration = dataRegisterStudent.registration();
  }

  public void updateStudent(DataUpdateStudent dataUpdateStudent) {
    if(dataUpdateStudent.name() != null && !dataUpdateStudent.name().isEmpty()) {
      this.name = dataUpdateStudent.name();
    }
    if(dataUpdateStudent.registration() != null && !dataUpdateStudent.registration().isEmpty()) {
      this.registration = dataUpdateStudent.registration();
    }
  }

  public void patchStudent(DataUpdateStudent dataUpdateStudent) {
    if (dataUpdateStudent.name() != null && !dataUpdateStudent.name().isEmpty()) {
      this.name = dataUpdateStudent.name();
    }
    if (dataUpdateStudent.registration() != null && !dataUpdateStudent.registration().isEmpty()) {
      this.registration = dataUpdateStudent.registration();
    }
  }

}

package com.example.demo.domain.entity;

import com.example.demo.domain.DTO.studentCourse.DataRegisterStudentCourse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "student_course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentCourse {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  public UUID id;

  @ManyToOne
  @JoinColumn(name = "id_student")
  public Student student;

  @ManyToOne
  @JoinColumn(name = "id_course")
  public Course course;

  public StudentCourse(Student student, Course course) {
    this.student = student;
    this.course = course;
  }
}

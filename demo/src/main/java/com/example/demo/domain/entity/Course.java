package com.example.demo.domain.entity;

import com.example.demo.domain.DTO.courser.DataRegisterCourse;
import com.example.demo.domain.DTO.courser.DataUpdateCourse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "course")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue()
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  public UUID id;

  @Column(name = "name", nullable = false)
  public String name;

  @Column(name = "description", nullable = false)
  public String description;

  @OneToMany(mappedBy = "course")
  public List<StudentCourse> courseStudents = new ArrayList<>();

  public Course(DataRegisterCourse dataRegisterCourse) {
    this.name = dataRegisterCourse.name();
    this.description = dataRegisterCourse.description();
  }

  public void updateCourse(DataUpdateCourse dataUpdateCourse) {
    if(dataUpdateCourse.name() != null && !dataUpdateCourse.name().isEmpty()) {
      this.name = dataUpdateCourse.name();
    }
    if(dataUpdateCourse.description() != null && !dataUpdateCourse.description().isEmpty()) {
      this.description = dataUpdateCourse.description();
    }
  }

  public void patchCourse(DataUpdateCourse dataUpdateCourse) {
    if(dataUpdateCourse.name() != null && !dataUpdateCourse.name().isEmpty()) {
      this.name = dataUpdateCourse.name();
    }
    if(dataUpdateCourse.description() != null && !dataUpdateCourse.description().isEmpty()) {
      this.description = dataUpdateCourse.description();
    }
  }
}

package com.example.demo.domain.DTO.studentCourse;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DataRegisterStudentCourse(
    @NotNull
    UUID studentId,
    @NotNull
    UUID courseId
) {
}

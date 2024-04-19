package com.example.demo.domain.DTO.student;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterStudent(
    @NotBlank
    String name,
    @NotBlank
    String registration
) {
}

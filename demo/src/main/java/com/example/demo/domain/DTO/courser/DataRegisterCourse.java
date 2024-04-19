package com.example.demo.domain.DTO.courser;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCourse(
    @NotBlank
    String name,
    @NotBlank
    String description
) {
}

package br.com.matteusmoreno.course_management.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateStudentRequest(
        @NotNull(message = "ID is required")
        UUID id,
        String name,
        LocalDate birthDate,
        @Email(message = "Invalid email format")
        String email,
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF format")
        String cpf,
        @NotBlank @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid CEP format")
        String cep) {
}

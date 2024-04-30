package br.com.matteusmoreno.course_management.response;

import br.com.matteusmoreno.course_management.domain.Address;
import br.com.matteusmoreno.course_management.domain.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentDetailsResponse(
        UUID id,
        String name,
        LocalDate birthDate,
        Integer age,
        String email,
        String cpf,
        Address address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean active){

    public StudentDetailsResponse(Student student) {
        this(student.getId(), student.getName(), student.getBirthDate(), student.getAge(), student.getEmail(), student.getCpf(),
                student.getAddress(), student.getCreatedAt(), student.getUpdatedAt(), student.getDeletedAt(), student.getActive());
    }
}

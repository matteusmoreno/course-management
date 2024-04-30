package br.com.matteusmoreno.course_management.response;

import br.com.matteusmoreno.course_management.domain.Student;

import java.util.UUID;

public record ListStudentResponse(
        UUID id,
        String name,
        Integer age,
        String email,
        Boolean active) {

    public ListStudentResponse(Student student) {
        this(student.getId(), student.getName(), student.getAge(), student.getEmail(), student.getActive());
    }
}

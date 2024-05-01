package br.com.matteusmoreno.course_management.repository;

import br.com.matteusmoreno.course_management.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    Page<Student> findAllByActiveTrue(Pageable pageable);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}

package br.com.matteusmoreno.course_management.service;

import br.com.matteusmoreno.course_management.domain.Student;
import br.com.matteusmoreno.course_management.repository.StudentRepository;
import br.com.matteusmoreno.course_management.request.CreateStudentRequest;
import br.com.matteusmoreno.course_management.request.UpdateStudentRequest;
import br.com.matteusmoreno.course_management.response.ListStudentResponse;
import br.com.matteusmoreno.course_management.utils.StudentUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private  final StudentUtils studentUtils;
    @Autowired
    public StudentService(StudentRepository studentRepository, StudentUtils studentUtils) {
        this.studentRepository = studentRepository;
        this.studentUtils = studentUtils;
    }

    //CREATE STUDENT
    public Student create(CreateStudentRequest request) {
        Student student = new Student();
        studentUtils.setAddressAttributes(request.cep(), student);
        BeanUtils.copyProperties(request, student);
        student.setAge(studentUtils.ageCalculator(student));
        student.setCreatedAt(LocalDateTime.now());
        student.setActive(true);

        studentRepository.save(student);

        return student;
    }

    //STUDENT DETAILS
    public Student detail(UUID id) {
        return studentRepository.findById(id).orElseThrow();
    }

    //LIST ACTIVE STUDENTS
    public Page<ListStudentResponse> list(Pageable pageable) {
        return studentRepository.findAllByActiveTrue(pageable).map(ListStudentResponse::new);
    }

    //UPDATE STUDENT
    public Student update(UpdateStudentRequest request) {
        Student student = studentRepository.findById(request.id()).orElseThrow();
        if (request.name() != null) {
            student.setName(request.name());
        }
        if (request.birthDate() != null) {
            student.setBirthDate(request.birthDate());
            student.setAge(studentUtils.ageCalculator(student));
        }

        if (request.email() != null) {
            student.setEmail(request.email());
        }
        if (request.cpf() != null) {
            student.setCpf(request.cpf());
        }
        if (request.cep() != null) {
            studentUtils.setAddressAttributes(request.cep(), student);
        }

        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);

        return student;
    }

    //DISABLE STUDENT
    public void disable(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setActive(false);
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }
}

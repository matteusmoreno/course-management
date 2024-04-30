package br.com.matteusmoreno.course_management.controller;

import br.com.matteusmoreno.course_management.domain.Student;
import br.com.matteusmoreno.course_management.request.CreateStudentRequest;
import br.com.matteusmoreno.course_management.request.UpdateStudentRequest;
import br.com.matteusmoreno.course_management.response.ListStudentResponse;
import br.com.matteusmoreno.course_management.response.StudentDetailsResponse;
import br.com.matteusmoreno.course_management.service.StudentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<StudentDetailsResponse> create(@RequestBody @Valid CreateStudentRequest request, UriComponentsBuilder uriBuilder) {
        Student student = studentService.create(request);
        URI uri = uriBuilder.path("/students/create/{id}").buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(uri).body(new StudentDetailsResponse(student));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<StudentDetailsResponse> detail(@PathVariable UUID id) {
        Student student = studentService.detail(id);

        return ResponseEntity.ok(new StudentDetailsResponse(student));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ListStudentResponse>> list(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        Page<ListStudentResponse> page = studentService.list(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<StudentDetailsResponse> update(@RequestBody @Valid UpdateStudentRequest request) {
        Student student = studentService.update(request);

        return ResponseEntity.ok(new StudentDetailsResponse(student));
    }

    @DeleteMapping("/disable/{id}")
    @Transactional
    public ResponseEntity<Void> disable(@PathVariable UUID id) {
        studentService.disable(id);

        return ResponseEntity.noContent().build();
    }
}

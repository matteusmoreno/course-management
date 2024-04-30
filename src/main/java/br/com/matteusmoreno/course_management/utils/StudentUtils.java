package br.com.matteusmoreno.course_management.utils;

import br.com.matteusmoreno.course_management.client.ViaCepClient;
import br.com.matteusmoreno.course_management.domain.Address;
import br.com.matteusmoreno.course_management.domain.Student;
import br.com.matteusmoreno.course_management.exception.InvalidCepException;
import br.com.matteusmoreno.course_management.request.CreateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class StudentUtils {

    private final ViaCepClient viaCepClient;
    @Autowired
    public StudentUtils(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public Integer ageCalculator(Student student) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = student.getBirthDate();

        return Period.between(birthDate, currentDate).getYears();
    }

    public void setAddressAttributes(String cep, Student student) {
        Address address = viaCepClient.getAddress(cep);
        if (address.getBairro() == null) throw new InvalidCepException("Invalid Cep: " + cep);

        student.setAddress(address);
    }
}

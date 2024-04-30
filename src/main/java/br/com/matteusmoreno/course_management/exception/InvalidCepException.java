package br.com.matteusmoreno.course_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidCepException extends RuntimeException{

    public InvalidCepException(String message) {
        super(message);
    }
}

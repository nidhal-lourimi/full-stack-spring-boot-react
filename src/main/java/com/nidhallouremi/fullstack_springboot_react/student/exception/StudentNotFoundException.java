package com.nidhallouremi.fullstack_springboot_react.student.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String msg) {
        super(msg);
    }

}

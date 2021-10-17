package com.nidhallouremi.fullstack_springboot_react.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudent(){
        List<Student> students = Arrays.asList(
                new Student(1L,"jamil","jamil@gmai.com",Gender.Male),
                new Student(2L,"amira","amira@Gmail.com",Gender.Female),
                new Student(3L,"samir","samir@Gmail.com",Gender.Male) );

        return students;
    }
}
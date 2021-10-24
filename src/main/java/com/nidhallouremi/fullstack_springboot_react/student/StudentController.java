package com.nidhallouremi.fullstack_springboot_react.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent() {
        /*throw new IllegalStateException ("error");*/
       return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }
}

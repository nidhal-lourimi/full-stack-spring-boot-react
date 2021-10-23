package com.nidhallouremi.fullstack_springboot_react.student;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        //add paging
        return studentRepository.findAll();
    }

    public void addStudent(Student student)
    {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

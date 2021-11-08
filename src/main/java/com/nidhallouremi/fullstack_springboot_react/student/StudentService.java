package com.nidhallouremi.fullstack_springboot_react.student;


import com.nidhallouremi.fullstack_springboot_react.student.exception.BadRequestException;
import com.nidhallouremi.fullstack_springboot_react.student.exception.StudentNotFoundException;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        //add paging
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        boolean studentExist = studentRepository.selectExistEmail(student.getEmail());
        if (studentExist) {
            throw new BadRequestException(String.format("email %s is taken", student.getEmail()));
        } else {
            studentRepository.save(student);
        }
    }



    public void deleteStudent(Long id)  {
        boolean exist =(studentRepository.getStudentById(id)).isPresent();
        if (exist){
            studentRepository.deleteById(id);

        }
        else{
            throw new StudentNotFoundException("student with id "+id+ " was not found");

        }

    }


}

package com.nidhallouremi.fullstack_springboot_react.student;

import com.nidhallouremi.fullstack_springboot_react.student.exception.BadRequestException;
import com.nidhallouremi.fullstack_springboot_react.student.exception.StudentNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@TestPropertySource(locations="classpath:application.properties")
//all the commented code was replaced by this annotation
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    //private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
         //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest=new StudentService(studentRepository);
    }

 /*   @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }*/


    @Test
    void canGetAllStudents() {
        //when
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();


    }

    @Test
    void canAddStudent() {
        //given
        String email ="jamila@gmail.com";
        Student student= new Student(
                "jamila",
                email,
                Gender.Female
                       );
        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void willThrowWhenEmailisTaken() {
        //given
        String email ="jamila@gmail.com";
        Student student= new Student(
                "jamila",
                email,
                Gender.Female
        );
        given(studentRepository.selectExistEmail(student.getEmail())).willReturn(true);

        //when
        //then
         //underTest.addStudent(student);

      assertThatThrownBy(()-> underTest.addStudent(student))
              .isInstanceOf(BadRequestException.class)
              .hasMessage(String.format("email jamila@gmail.com is taken"));

      verify(studentRepository,never()).save(any());
    }


 // https://gabrielpulga.medium.com/a-beginners-guide-to-unit-testing-crud-endpoints-of-a-spring-boot-java-web-service-api-8ae342c9cbcd
/* @InjectMocks
 private DeleteUserService deleteUserService;*/
    @Test
   void canDeleteStudent() {
        // given
        String email ="jamil546478@gmail.com";
        Student student= new Student(1L,
                "jamil",
                email,
                Gender.Female
        );
    when(studentRepository.getStudentById(student.getId())).thenReturn(Optional.of(student));
        //studentRepository.save(student);
        underTest.deleteStudent(student.getId());

        // when
        // then
       // verify(studentRepository).save(student);
        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        // given
        long id = 10;
     /*   given(studentRepository.existsById(id))
                .willReturn(false);*/
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("student with id "+id+ " was not found");

        verify(studentRepository, never()).deleteById(any());
    }
}
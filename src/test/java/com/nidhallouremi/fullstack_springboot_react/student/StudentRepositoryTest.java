package com.nidhallouremi.fullstack_springboot_react.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.assertj.core.api.Assertions.*;
@TestPropertySource(locations="classpath:application.properties")
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private  StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfSelectedEmailExist() {
        //given
        Student student = new  Student("jamila","jamila@gmail.com",Gender.Female );
        underTest.save(student);
        String email= "jamila@gmail.com";
        // when
       boolean expected= underTest.selectExistEmail(email);
       //then

        assertThat(expected).isTrue();



    }
    @Test
    void itShouldCheckIfSelectedEmailDoesNotExist() {
        //given
        Student student = new  Student("jamila","jamila@gmail.com",Gender.Female );
        underTest.save(student);
        String email= "xoxo@gmail.com";
        // when
        boolean expected= underTest.selectExistEmail(email);
        //then

        assertThat(expected).isFalse();



    }
}
package com.nidhallouremi.fullstack_springboot_react.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Student> getStudentById(Long id) ;

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Student s " +
            "WHERE s.email = ?1"
    )
    boolean selectExistEmail(String mail);
    //Optional<Student> findStudentsByEmail(String email);
}

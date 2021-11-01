package com.nidhallouremi.fullstack_springboot_react.student;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence",sequenceName = "student_sequence",allocationSize = 1,initialValue = 1000)
    @GeneratedValue(generator = "student_sequence",strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String name;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public Student(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}

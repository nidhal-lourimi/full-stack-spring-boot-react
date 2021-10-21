package com.nidhallouremi.fullstack_springboot_react.student;

import lombok.*;

import javax.persistence.*;

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
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Student(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}

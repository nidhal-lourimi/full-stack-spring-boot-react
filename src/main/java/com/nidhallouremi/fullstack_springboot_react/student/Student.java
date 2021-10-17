package com.nidhallouremi.fullstack_springboot_react.student;

import lombok.*;

@ToString
@Getter
@Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Student {

    private Long id;
    private String name;
    private String email;
    private Gender gender;

}

package com.example.testRestAPI.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}

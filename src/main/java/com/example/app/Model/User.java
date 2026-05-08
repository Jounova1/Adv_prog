package com.example.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;

    private Integer age;
    private String qualification;
    private String experience;

    private boolean active;

    private String role;

     
}
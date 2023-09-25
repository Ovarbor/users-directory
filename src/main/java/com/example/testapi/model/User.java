package com.example.TestAPI.model;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_sur_name")
    private String surName;

    @Past(message = "Дата рождения не может быть в будущем.")
    private LocalDate birthday;
}

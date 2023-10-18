package com.example.mainservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "usr")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank
    private String name;

    @Column(name = "user_last_name")
    @NotBlank
    private String lastName;

    @Column(name = "user_sur_name")
    @NotBlank
    private String surName;

    @Past(message = "Дата рождения не может быть в будущем.")
    @NotNull
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}

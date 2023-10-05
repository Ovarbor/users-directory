package com.example.testapi.model;
import lombok.*;
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
@Table(name = "users")
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

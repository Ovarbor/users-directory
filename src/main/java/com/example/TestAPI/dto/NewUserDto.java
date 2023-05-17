package com.example.TestAPI.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    private String name;
    private String lastName;
    private String surName;
    private LocalDate birthday;
}

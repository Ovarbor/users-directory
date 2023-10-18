package com.example.mainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewContactInfoDto {


    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private Long user;
}

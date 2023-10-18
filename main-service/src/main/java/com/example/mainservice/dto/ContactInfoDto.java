package com.example.mainservice.dto;

import com.example.mainservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {

    private Long id;

    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private User user;
}

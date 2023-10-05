package com.example.testapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDtoUpdate {

    private Long id;

    @Email(message = "Invalid email")
    private String email;

    private String phone;

    @NotNull
    private Long user;
}

package com.example.TestAPI.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDtoGet {

    private Long id;

    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private UserDtoGet user;
}

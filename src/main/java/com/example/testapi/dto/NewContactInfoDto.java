package com.example.TestAPI.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewContactInfoDto {

    private String phone;

    @Email(message = "Invalid email")
    private String email;

    private Long user;
}

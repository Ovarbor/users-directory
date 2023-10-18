package com.example.mainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessDataDto {

    private Long id;
    private String username;
    private String password;
    @Builder.Default
    private Boolean enabled = true;
}

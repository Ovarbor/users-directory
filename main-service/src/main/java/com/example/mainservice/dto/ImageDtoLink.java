package com.example.mainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDtoLink {

        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String type;

        @NotBlank
        private String link;
}

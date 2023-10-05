package com.example.testapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

        private Long id;

        @NotBlank
        private String title;
        @NotBlank
        private String type;
        @NotNull
        private byte[] data;
}

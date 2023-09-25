package com.example.TestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

        private Long id;
        private String title;
        private String type;
        private byte[] data;
}

package com.example.TestAPI.service;

import com.example.TestAPI.dto.ImageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    ImageDto findImage(Long id);

    ImageDto addImage(MultipartFile multipartFile) throws Exception;

    void removeImage(Long id);
}

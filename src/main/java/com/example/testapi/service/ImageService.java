package com.example.TestAPI.service;
import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.dto.ImageDtoLink;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public interface ImageService {

    ImageDto findImage(Long id);

    List<ImageDtoLink> getAllImages(int from, int size);

    ImageDtoLink getImage(Long id);

    ImageDto addImage(MultipartFile multipartFile) throws Exception;

    void removeImage(Long id);
}

package com.example.TestAPI.service;

import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.dto.NewImageDto;
import com.example.TestAPI.exceptions.ImageNotFoundException;
import com.example.TestAPI.mapper.ImageMapper;
import com.example.TestAPI.model.Image;
import com.example.TestAPI.repo.ImageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepo imageRepo;


    @Override
    public ImageDto findImage(Long id) {
        Image image = imageRepo.findById(id).orElseThrow(() ->
                new ImageNotFoundException("Image with id " + id + " not found"));
        return imageMapper.toImageDto(image);
    }

    @Override
    public ImageDto addImage(MultipartFile multipartFile) throws Exception {
        NewImageDto newImageDto = new NewImageDto(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getBytes());
        ImageDto imageDto = imageMapper.toImageDto(newImageDto);
        return imageMapper.toImageDto(imageRepo.save(imageMapper.toImage(imageDto)));
    }

    @Override
    public void removeImage(Long id) {
        if (!imageRepo.existsById(id)) {
           throw new ImageNotFoundException("Image with id " + id + " not found");
        }
        imageRepo.deleteById(id);
    }
}

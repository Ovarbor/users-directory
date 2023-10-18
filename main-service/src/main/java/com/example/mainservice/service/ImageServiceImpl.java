package com.example.mainservice.service;
import com.example.mainservice.Utils.CreateImageLink;
import com.example.mainservice.dto.ImageDto;
import com.example.mainservice.dto.ImageDtoLink;
import com.example.mainservice.dto.NewImageDto;
import com.example.mainservice.exceptions.ConflictException;
import com.example.mainservice.exceptions.NotFoundValidationException;
import com.example.mainservice.mapper.ImageMapper;
import com.example.mainservice.model.Image;
import com.example.mainservice.repo.ImageRepo;
import com.example.mainservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepo imageRepo;
    private final UserRepo userRepo;


    @Override
    public ImageDto findImage(Long id) {
        Image image = imageRepo.findById(id).orElseThrow(() ->
                new NotFoundValidationException("Image with id " + id + " not found"));
        return imageMapper.toImageDto(image);
    }

    @Override
    public List<ImageDtoLink> getAllImages(int from, int size) {
        List<Image> images = imageRepo.findAll(PageRequest.of(from, size, Sort.by("id").ascending())).toList();
        List<ImageDtoLink> imageDtoLinks = imageMapper.toImageDtoLinkList(images);
        imageDtoLinks.forEach(imageDtoLink -> imageDtoLink.setLink(CreateImageLink.createImageLink(imageDtoLink.getId())));
        return imageDtoLinks;
    }

    @Override
    public ImageDtoLink getImage(Long id) {
        Image image = imageRepo.findById(id).orElseThrow(() ->
                new NotFoundValidationException("Image with id " + id + " not found"));
        ImageDtoLink imageDtoLink = imageMapper.toImageDtoLink(image);
        imageDtoLink.setLink(CreateImageLink.createImageLink(id));
        return imageDtoLink;
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
           throw new NotFoundValidationException("Image with id " + id + " not found");
        }
        if (userRepo.countUserByImageId(id) != 0) {
            throw new ConflictException("Image with id " + id + " still in use");
        }
        imageRepo.deleteById(id);
    }
}

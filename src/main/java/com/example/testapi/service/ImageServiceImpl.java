package com.example.TestAPI.service;
import com.example.TestAPI.Utils.CreateImageLink;
import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.dto.ImageDtoLink;
import com.example.TestAPI.dto.NewImageDto;
import com.example.TestAPI.exceptions.ConflictException;
import com.example.TestAPI.exceptions.IllegalRequestException;
import com.example.TestAPI.exceptions.ImageNotFoundException;
import com.example.TestAPI.mapper.ImageMapper;
import com.example.TestAPI.model.Image;
import com.example.TestAPI.repo.ImageRepo;
import com.example.TestAPI.repo.UserRepo;
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
                new ImageNotFoundException("Image with id " + id + " not found"));
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
                new ImageNotFoundException("Image with id " + id + " not found"));
        ImageDtoLink imageDtoLink = imageMapper.toImageDtoLink(image);
        imageDtoLink.setLink(CreateImageLink.createImageLink(id));
        return imageDtoLink;
    }

    @Override
    public ImageDto addImage(MultipartFile multipartFile) throws Exception {
        NewImageDto newImageDto = new NewImageDto(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getBytes());
//        checkFile(newImageDto);
        ImageDto imageDto = imageMapper.toImageDto(newImageDto);
        return imageMapper.toImageDto(imageRepo.save(imageMapper.toImage(imageDto)));
    }

//    private void checkFile(NewImageDto newImageDto) {
//        if (newImageDto.getTitle().isBlank() || newImageDto.getType().isBlank() ||
//        newImageDto.getData().length == 0) {
//            throw new IllegalRequestException("Incorrect file properties");
//        }
//    }

    @Override
    public void removeImage(Long id) {
        if (!imageRepo.existsById(id)) {
           throw new ImageNotFoundException("Image with id " + id + " not found");
        }
        if (userRepo.countUserByImageId(id) != 0) {
            throw new ConflictException("Image with id " + id + " still in use");
        }
        imageRepo.deleteById(id);
    }
}

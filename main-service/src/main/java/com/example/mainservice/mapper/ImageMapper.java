package com.example.mainservice.mapper;

import com.example.mainservice.dto.ImageDto;
import com.example.mainservice.dto.ImageDtoLink;
import com.example.mainservice.dto.NewImageDto;
import com.example.mainservice.model.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto imageDto);

    ImageDto toImageDto(NewImageDto newImageDto);

    List<ImageDtoLink> toImageDtoLinkList(List<Image> imageList);

    ImageDtoLink toImageDtoLink(Image image);

    ImageDto toImageDto(Image image);
}

package com.example.TestAPI.mapper;
import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.dto.ImageDtoLink;
import com.example.TestAPI.dto.NewImageDto;
import com.example.TestAPI.model.Image;
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

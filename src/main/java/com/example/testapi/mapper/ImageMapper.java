package com.example.testapi.mapper;
import com.example.testapi.dto.ImageDto;
import com.example.testapi.dto.ImageDtoLink;
import com.example.testapi.dto.NewImageDto;
import com.example.testapi.model.Image;
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

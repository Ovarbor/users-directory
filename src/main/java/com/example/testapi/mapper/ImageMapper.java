package com.example.TestAPI.mapper;
import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.dto.NewImageDto;
import com.example.TestAPI.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto imageDto);

    ImageDto toImageDto(NewImageDto newImageDto);

    ImageDto toImageDto(Image image);
}

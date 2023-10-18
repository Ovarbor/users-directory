package com.example.mainservice.mapper;
import com.example.mainservice.dto.AccessDataDto;
import com.example.mainservice.dto.NewAccessDataDto;
import com.example.mainservice.model.AccessData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessDataMapper {

    AccessDataDto toUserDto (NewAccessDataDto newAccessDataDto);

    AccessData toUser (AccessDataDto accessDataDto);

    AccessDataDto toUserDto (AccessData accessData);

}

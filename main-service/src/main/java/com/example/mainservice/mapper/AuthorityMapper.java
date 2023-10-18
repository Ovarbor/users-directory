package com.example.mainservice.mapper;
import com.example.mainservice.dto.NewAccessDataDto;
import com.example.mainservice.model.Authority;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    Authority toAuthority(NewAccessDataDto newAccessDataDto);
}

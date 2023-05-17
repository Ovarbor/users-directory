package com.example.testapi.mapper;
import com.example.testapi.dto.ContactInfoDto;
import com.example.testapi.dto.NewContactInfoDto;
import com.example.testapi.model.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfoDto toContactInfoDto(ContactInfo contactInfo);

    @Mapping(source = "user", target = "user.id")
    ContactInfo toContactInfo(NewContactInfoDto newContactInfoDto);

    List<ContactInfoDto> toContactInfoDtoList(List<ContactInfo> contactInfoList);
}

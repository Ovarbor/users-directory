package com.example.TestAPI.mapper;
import com.example.TestAPI.dto.ContactInfoDto;
import com.example.TestAPI.dto.NewContactInfoDto;
import com.example.TestAPI.model.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring", uses = {ContactInfoMapper.class, UserMapper.class})
public interface ContactInfoMapper {

    ContactInfoDto toContactInfoDto(ContactInfo contactInfo);

    @Mapping(source = "user", target = "user.id")
    ContactInfo toContactInfo(NewContactInfoDto newContactInfoDto);

    List<ContactInfoDto> toContactInfoDtoList(List<ContactInfo> contactInfoList);
}

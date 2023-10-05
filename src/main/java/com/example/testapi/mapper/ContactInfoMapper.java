package com.example.testapi.mapper;
import com.example.testapi.dto.ContactInfoDto;
import com.example.testapi.dto.ContactInfoDtoGet;
import com.example.testapi.dto.NewContactInfoDto;
import com.example.testapi.model.ContactInfo;
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

    List<ContactInfoDtoGet> toContactInfoDtoGetList(List<ContactInfo> contactInfoList);

    ContactInfoDtoGet toContactInfoDtoGet(ContactInfoDto contactInfoDto);
}

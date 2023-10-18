package com.example.mainservice.service;
import com.example.mainservice.dto.ContactInfoDto;
import com.example.mainservice.dto.ContactInfoDtoGet;
import com.example.mainservice.dto.ContactInfoDtoUpdate;
import com.example.mainservice.dto.NewContactInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactInfoService {

    ContactInfoDto addContactInfo(NewContactInfoDto newContactInfoDto);

    ContactInfoDtoGet getContactInfo(Long infoId);

    List<ContactInfoDtoGet> getAllContactInfo(int from, int size);

    ContactInfoDto updateContactInfo(Long infoId, ContactInfoDtoUpdate contactInfoDtoUpdate);

    void removeContactInfo(Long infoId);
}


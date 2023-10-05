package com.example.testapi.service;
import com.example.testapi.dto.ContactInfoDto;
import com.example.testapi.dto.ContactInfoDtoGet;
import com.example.testapi.dto.ContactInfoDtoUpdate;
import com.example.testapi.dto.NewContactInfoDto;
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


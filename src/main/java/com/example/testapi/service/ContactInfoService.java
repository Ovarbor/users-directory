package com.example.TestAPI.service;
import com.example.TestAPI.dto.ContactInfoDto;
import com.example.TestAPI.dto.ContactInfoDtoGet;
import com.example.TestAPI.dto.ContactInfoDtoUpdate;
import com.example.TestAPI.dto.NewContactInfoDto;
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


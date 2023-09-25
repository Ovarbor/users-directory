package com.example.TestAPI.service;
import com.example.TestAPI.dto.ContactInfoDto;
import com.example.TestAPI.dto.NewContactInfoDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ContactInfoService {

    ContactInfoDto addContactInfo(NewContactInfoDto newContactInfoDto);

    ContactInfoDto findContactInfo(Long infoId);

    List<ContactInfoDto> findAllContactInfo(int from, int size);

    ContactInfoDto updateContactInfo(Long infoId, ContactInfoDto contactInfoDto);

    void removeContactInfo(Long infoId);

}


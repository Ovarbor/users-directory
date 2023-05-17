package com.example.testapi.service;
import com.example.testapi.dto.ContactInfoDto;
import com.example.testapi.dto.NewContactInfoDto;
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


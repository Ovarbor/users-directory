package com.example.testapi.service;

import com.example.testapi.dto.ContactInfoDto;
import com.example.testapi.dto.NewContactInfoDto;
import com.example.testapi.exceptions.NotFoundValidationException;
import com.example.testapi.mapper.ContactInfoMapper;
import com.example.testapi.model.ContactInfo;
import com.example.testapi.model.User;
import com.example.testapi.repo.ContactInfoRepo;
import com.example.testapi.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepo contactInfoRepo;
    private final ContactInfoMapper contactInfoMapper;
    private final UserRepo userRepo;

    @Override
    public ContactInfoDto addContactInfo(NewContactInfoDto newContactInfoDto) {
        ContactInfo contactInfo = contactInfoMapper.toContactInfo(newContactInfoDto);
        User user = findUserEntity(newContactInfoDto.getUser());
        contactInfo.setUser(user);
        return contactInfoMapper.toContactInfoDto(contactInfoRepo.save(contactInfo));
    }

    private User findUserEntity(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new NotFoundValidationException("User with id: " + id + " was not found")
        );
    }

    @Override
    public ContactInfoDto findContactInfo(Long infoId) {
        ContactInfo contactInfo = contactInfoRepo.findById(infoId).orElseThrow(() ->
                new NotFoundValidationException("ContactInfo with id: " + infoId + " not found"));
        return contactInfoMapper.toContactInfoDto(contactInfo);
    }

    @Override
    public List<ContactInfoDto> findAllContactInfo(int from, int size) {
        List<ContactInfo> contactInfoList = contactInfoRepo.findAll(PageRequest.of(from, size, Sort.by("id").ascending())).toList();
        return contactInfoMapper.toContactInfoDtoList(contactInfoList);
    }

    @Override
    public ContactInfoDto updateContactInfo(Long infoId, ContactInfoDto contactInfoDto) {
        ContactInfo oldInfo = contactInfoRepo.findById(infoId).orElseThrow(() ->
                new NotFoundValidationException("Contact with id: " + contactInfoDto.getId() + " not found"));
        ContactInfo newContactInfo = infoPhoneAndEmailUpdate(oldInfo, contactInfoDto);
        return contactInfoMapper.toContactInfoDto(contactInfoRepo.save(newContactInfo));
    }

    @Override
    public void removeContactInfo(Long infoId) {
        if (!contactInfoRepo.existsById(infoId)) {
            throw new NotFoundValidationException("ContactInfo with id: " + infoId + " not found");
        }
        contactInfoRepo.deleteById(infoId);
    }

    private ContactInfo infoPhoneAndEmailUpdate(ContactInfo oldContactInfo, ContactInfoDto contactInfoDto) {
        if (contactInfoDto.getEmail() != null) {
            if (!contactInfoDto.getEmail().isBlank()) {
                oldContactInfo.setEmail(contactInfoDto.getEmail());
            }
        }
        if (contactInfoDto.getPhone() != null) {
            if (!contactInfoDto.getPhone().isBlank()) {
                oldContactInfo.setPhone(contactInfoDto.getPhone());
            }
        }
        return oldContactInfo;
    }
}

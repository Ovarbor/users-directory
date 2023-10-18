package com.example.mainservice.service;
import com.example.mainservice.Utils.CreateImageLink;
import com.example.mainservice.dto.ContactInfoDto;
import com.example.mainservice.dto.ContactInfoDtoGet;
import com.example.mainservice.dto.ContactInfoDtoUpdate;
import com.example.mainservice.dto.NewContactInfoDto;
import com.example.mainservice.exceptions.NotFoundValidationException;
import com.example.mainservice.mapper.ContactInfoMapper;
import com.example.mainservice.model.ContactInfo;
import com.example.mainservice.model.User;
import com.example.mainservice.repo.ContactInfoRepo;
import com.example.mainservice.repo.UserRepo;
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
    public ContactInfoDtoGet getContactInfo(Long infoId) {
        ContactInfo contactInfo = contactInfoRepo.findById(infoId).orElseThrow(() ->
                new NotFoundValidationException("Contact with id " + infoId + " not found"));
        ContactInfoDto contactInfoDto = contactInfoMapper.toContactInfoDto(contactInfo);
        ContactInfoDtoGet contactInfoDtoGet = contactInfoMapper.toContactInfoDtoGet(contactInfoDto);
        if (contactInfoDtoGet.getUser().getImage() != null) {
            contactInfoDtoGet.getUser().getImage()
                    .setLink(CreateImageLink.createImageLink(contactInfoDtoGet.getUser().getImage().getId()));
        }
        return contactInfoDtoGet;
    }

    @Override
    public List<ContactInfoDtoGet> getAllContactInfo(int from, int size) {
        List<ContactInfo> contactInfoList = contactInfoRepo
                .findAll(PageRequest.of(from, size, Sort.by("id").ascending())).toList();
        List<ContactInfoDtoGet> contactInfoDtoGets = contactInfoMapper.toContactInfoDtoGetList(contactInfoList);
        contactInfoDtoGets.stream().filter(contactInfoDtoGet -> contactInfoDtoGet.getUser().getImage() != null)
                .forEach(contactInfoDtoGet -> contactInfoDtoGet.getUser().getImage()
                        .setLink(CreateImageLink.createImageLink(contactInfoDtoGet.getUser().getImage().getId())));
        return contactInfoDtoGets;
    }

    @Override
    public ContactInfoDto updateContactInfo(Long infoId, ContactInfoDtoUpdate contactInfoDtoUpdate) {
        ContactInfo oldInfo = contactInfoRepo.findById(infoId).orElseThrow(() ->
                new NotFoundValidationException("Contact with id " + infoId + " not found"));
        ContactInfo newContactInfo = infoPhoneAndEmailUpdate(oldInfo, contactInfoDtoUpdate);
        return contactInfoMapper.toContactInfoDto(contactInfoRepo.save(newContactInfo));
    }

    @Override
    public void removeContactInfo(Long infoId) {
        if (!contactInfoRepo.existsById(infoId)) {
            throw new NotFoundValidationException("Contact with id " + infoId + " not found");
        }
        contactInfoRepo.deleteById(infoId);
    }

    private ContactInfo infoPhoneAndEmailUpdate(ContactInfo oldContactInfo, ContactInfoDtoUpdate contactInfoDtoUpdate) {
        if (contactInfoDtoUpdate.getEmail() != null) {
            if (!contactInfoDtoUpdate.getEmail().isBlank()) {
                oldContactInfo.setEmail(contactInfoDtoUpdate.getEmail());
            }
        }
        if (contactInfoDtoUpdate.getPhone() != null) {
            if (!contactInfoDtoUpdate.getPhone().isBlank()) {
                oldContactInfo.setPhone(contactInfoDtoUpdate.getPhone());
            }
        }
        if (contactInfoDtoUpdate.getUser() != null) {
            oldContactInfo.setUser(userRepo.findById(contactInfoDtoUpdate.getUser()).orElseThrow(() ->
                    new NotFoundValidationException("User with id " + contactInfoDtoUpdate.getUser() + " not found")));
        }
        return oldContactInfo;
    }
}

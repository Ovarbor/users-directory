package com.example.mainservice.service;
import com.example.mainservice.dto.AccessDataDto;
import com.example.mainservice.dto.NewAccessDataDto;
import com.example.mainservice.exceptions.ConflictException;
import com.example.mainservice.mapper.AccessDataMapper;
import com.example.mainservice.mapper.AuthorityMapper;
import com.example.mainservice.model.AccessData;
import com.example.mainservice.model.Authority;
import com.example.mainservice.model.Role;
import com.example.mainservice.repo.AccessDataRepo;
import com.example.mainservice.repo.AuthorityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Configuration
@RequiredArgsConstructor
public class AccessDataServiceImpl implements AccessDataService {

    private final AccessDataRepo accessDataRepo;
    private final AccessDataMapper accessDataMapper;
    private final AuthorityRepo authorityRepo;
    private final AuthorityMapper authorityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessDataDto addData(NewAccessDataDto newAccessDataDto) {
        validate(newAccessDataDto);
        newAccessDataDto.setPassword(passwordEncoder.encode(newAccessDataDto.getPassword()));
        AccessDataDto accessDataDto = accessDataMapper.toUserDto(newAccessDataDto);
        AccessData accessData = accessDataRepo.save(accessDataMapper.toUser(accessDataDto));
        Authority authority = authorityMapper.toAuthority(newAccessDataDto);
        authority.setRole(Role.ROLE_USER);
        authorityRepo.save(authority);
        return accessDataMapper.toUserDto(accessData);
    }

    private void validate(NewAccessDataDto newAccessDataDto) {
        Set<String> categoryNames = new HashSet<>(accessDataRepo.findUserByName());
        if (categoryNames.contains(newAccessDataDto.getUsername())) {
            throw new ConflictException("AccessData username " + newAccessDataDto.getUsername() + " already used");
        }
    }
}

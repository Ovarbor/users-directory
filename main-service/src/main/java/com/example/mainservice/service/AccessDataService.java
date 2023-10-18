package com.example.mainservice.service;

import com.example.mainservice.dto.AccessDataDto;
import com.example.mainservice.dto.NewAccessDataDto;
import org.springframework.stereotype.Service;

@Service
public interface AccessDataService {

    AccessDataDto addData(NewAccessDataDto newAccessDataDto);
}

package com.example.mainservice.controller;
import com.example.mainservice.dto.AccessDataDto;
import com.example.mainservice.dto.NewAccessDataDto;
import com.example.mainservice.service.AccessDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access")
public class AccessDataController {

    private final AccessDataService accessDataService;

    @PostMapping()
     public ResponseEntity<AccessDataDto> addData(@RequestBody NewAccessDataDto newAccessDataDto) {
        return ResponseEntity.status(201).body(accessDataService.addData(newAccessDataDto));
    }
}

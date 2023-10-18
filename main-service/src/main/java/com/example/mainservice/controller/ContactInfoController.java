package com.example.mainservice.controller;

import com.example.mainservice.dto.ContactInfoDto;
import com.example.mainservice.dto.ContactInfoDtoGet;
import com.example.mainservice.dto.ContactInfoDtoUpdate;
import com.example.mainservice.dto.NewContactInfoDto;
import com.example.mainservice.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/info")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @PostMapping
    public ResponseEntity<ContactInfoDto> postContactInfo(@Valid @RequestBody NewContactInfoDto newContactInfoDto) {
            log.info("POST: /info");
            return ResponseEntity.status(201).body(contactInfoService.addContactInfo(newContactInfoDto));
    }

    @GetMapping("/{infoId}")
    public ResponseEntity<ContactInfoDtoGet> getContactInfo(@PathVariable Long infoId) {
        log.info("GET /info/{}", infoId);
        return ResponseEntity.ok().body(contactInfoService.getContactInfo(infoId));
    }

    @GetMapping
    ResponseEntity<List<ContactInfoDtoGet>> getContactInfos(
            @RequestParam(value = "from", defaultValue = "0")
            @PositiveOrZero Integer from,
            @RequestParam(value = "size", defaultValue = "10")
            @Positive Integer size) {
        log.info("GET /info?from=" + from + "&size=" + size);
        return ResponseEntity.ok().body(contactInfoService.getAllContactInfo(from, size));
    }

    @PatchMapping("/{infoId}")
    public ResponseEntity<ContactInfoDto> updateContactInfo(@PathVariable Long infoId,
                                                   @Valid @RequestBody ContactInfoDtoUpdate contactInfoDtoUpdate) {
        log.info("PATCH: /info/{}", infoId);
        return ResponseEntity.ok().body(contactInfoService.updateContactInfo(infoId, contactInfoDtoUpdate));
    }

    @DeleteMapping("/{infoId}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long infoId) {
        contactInfoService.removeContactInfo(infoId);
        log.info("DELETE: /info/{}", infoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

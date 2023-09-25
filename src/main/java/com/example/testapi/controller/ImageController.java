package com.example.TestAPI.controller;
import com.example.TestAPI.dto.ImageDto;
import com.example.TestAPI.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;


    @GetMapping("/view/{imageId}")
    public ResponseEntity<Resource> getImageView(@PathVariable Long imageId) {
        ImageDto imageDto = imageService.findImage(imageId);
        ByteArrayResource body = new ByteArrayResource(imageDto.getData());
        log.info("GET /images/view/{}", imageId);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, imageDto.getType())
                .body(body);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDto> getImage(@PathVariable Long imageId) {
        log.info("GET /image/{}", imageId);
        return ResponseEntity.ok().body(imageService.findImage(imageId));
    }

    @PostMapping("/add")
        public ResponseEntity<ImageDto> addImage(@RequestParam ("file") @RequestPart MultipartFile multipartFile) throws Exception {
        log.info("POST: /images");
        return ResponseEntity.status(201).body(imageService.addImage(multipartFile));
    }

    @DeleteMapping("{imageId}")
    public ResponseEntity<Void> removeImage(@PathVariable Long imageId) {
        imageService.removeImage(imageId);
        log.info("DELETE /images/{}", imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String createImageLink(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/images/" + id)
                .toUriString();
    }
}

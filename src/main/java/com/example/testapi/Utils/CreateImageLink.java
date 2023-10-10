package com.example.testapi.Utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CreateImageLink {

    public static String createImageLink(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/images/view/" + id)
                .replaceQuery("")
                .toUriString();
    }
}

package com.synchrony.imageapi.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.synchrony.imageapi.dto.ImageRequest;
import com.synchrony.imageapi.dto.ImageResponse;
import com.synchrony.imageapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse uploadImage(@RequestParam("image") MultipartFile image,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "accountId", required = false) String accountId,
                                     @RequestParam(value = "accountUrl", required = false) String accountUrl)
            throws IOException, UnirestException {

        ImageRequest imageRequest = ImageRequest.builder()
                .title(title != null ? title : image.getOriginalFilename())
                .description(description)
                .accountId(accountId)
                .accountUrl(accountUrl).build();

        return imageService.uloadImage(imageRequest, image.getInputStream(), image.getOriginalFilename());

    }


    @DeleteMapping("{imgurlImageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable String imgurlImageId) throws UnirestException {
        imageService.deleteImage(imgurlImageId);
    }

    @GetMapping
    public List<ImageResponse> getImages() {
        return imageService.getImages();
    }

    @GetMapping("{imgurlImageId}")
    public ImageResponse getImages(@PathVariable String imgurlImageId) {
        return imageService.getImageById(imgurlImageId);
    }
}

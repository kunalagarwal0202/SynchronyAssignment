package com.synchrony.imageapi.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.synchrony.imageapi.dto.ImageRequest;
import com.synchrony.imageapi.dto.ImageResponse;
import com.synchrony.imageapi.dto.ImgurlImageResponseData;
import com.synchrony.imageapi.dto.ImgurlImageUploadResponseRoot;
import com.synchrony.imageapi.entity.Image;
import com.synchrony.imageapi.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImgurlImageService imgurlImageService;


    public ImageResponse uloadImage(ImageRequest imageRequest, InputStream imageFileInputStream, String fileName) throws UnirestException {
        ImgurlImageUploadResponseRoot imgurlImageUploadResponseRoot = imgurlImageService
                .uploadImage(imageRequest, imageFileInputStream, fileName);
        ImgurlImageResponseData data = imgurlImageUploadResponseRoot.getData();
        Image imageEntity = Image.builder()
                .imgurlImageId(data.getId())
                .accountId(data.getAccountId())
                .accountUrl(data.getAccountUrl())
                .description(data.getDescription())
                .link(data.getLink())
                .title(data.getTitle())
                .type(data.getType())
                .build();
        Image savedImage = imageRepository.save(imageEntity);

        return ImageResponse.builder().id(savedImage.getId())
                .accountUrl(savedImage.getAccountUrl())
                .type(savedImage.getType())
                .link(savedImage.getLink())
                .description(savedImage.getDescription())
                .accountId(savedImage.getAccountId())
                .imgurlImageId(savedImage.getImgurlImageId())
                .title(savedImage.getTitle())
                .build();
    }


    public void deleteImage(String imageId) throws UnirestException {
        imgurlImageService.deleteImage(imageId);

        imageRepository.deleteByImgurlImageId(imageId);
    }

    public ImageResponse getImageById(String imageId) {
        Image savedImage = imageRepository.findByImgurlImageId(imageId);
        if (savedImage == null) {
            throw new IllegalArgumentException("No image record with id: " + imageId);
        }
        return ImageResponse.builder().id(savedImage.getId())
                .accountUrl(savedImage.getAccountUrl())
                .type(savedImage.getType())
                .link(savedImage.getLink())
                .description(savedImage.getDescription())
                .accountId(savedImage.getAccountId())
                .imgurlImageId(savedImage.getImgurlImageId())
                .title(savedImage.getTitle())
                .build();
    }

    public List<ImageResponse> getImages() {

        List<Image> imageList = imageRepository.findAll();
        if (imageList != null && !imageList.isEmpty()) {

            return imageList.stream().map(image -> ImageResponse.builder()
                    .accountId(image.getAccountId())
                    .id(image.getId())
                    .imgurlImageId(image.getImgurlImageId())
                    .accountUrl(image.getAccountUrl())
                    .description(image.getDescription())
                    .link(image.getLink())
                    .title(image.getTitle())
                    .type(image.getType())
                    .build()
            ).collect(Collectors.toList());

        }

        return new ArrayList<>();
    }
}

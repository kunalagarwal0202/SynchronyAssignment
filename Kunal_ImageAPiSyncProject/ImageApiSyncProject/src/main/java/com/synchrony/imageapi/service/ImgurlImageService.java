package com.synchrony.imageapi.service;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.synchrony.imageapi.dto.ImageRequest;
import com.synchrony.imageapi.dto.ImgurlImageUploadResponseRoot;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ImgurlImageService {

    public ImgurlImageUploadResponseRoot uploadImage(ImageRequest imageRequest, InputStream imageFileInputStream, String fileName) throws UnirestException {

        HttpResponse<String> response = Unirest.post("https://api.imgur.com/3/image")
                .header("Authorization", "Client-ID 6f7d71c05691caa")
                .field("image", imageFileInputStream, fileName)
                .field("title", imageRequest.getTitle())
                .field("description", imageRequest.getDescription())
                .asString();

        if (response.getStatus() != 200) {
            throw new IllegalArgumentException("Unable to upload image.\nResponse from Imgurl api:\n" + response.getBody());
        }
        String body = response.getBody();
        return new Gson().fromJson(body, ImgurlImageUploadResponseRoot.class);

    }


    public void deleteImage(String imageId) throws UnirestException {

        HttpResponse<String> response = Unirest.delete("https://api.imgur.com/3/image/" + imageId)
                .header("Authorization", "Client-ID 6f7d71c05691caa")
                .asString();
        if (response.getStatus() != 200) {
            throw new IllegalArgumentException("Deleting image with id "
                    + imageId + " failed.\nResponse from Imgurl api:\n" + response.getBody());
        }
    }

    public ImgurlImageUploadResponseRoot getImage(String imageId) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://api.imgur.com/3/image/y9ie")
                .header("Authorization", "Client-ID 6f7d71c05691caa")
                .header("Cookie", "IMGURSESSION=c84a614deb494a633e99914741c9e576; _nc=1; accesstoken=1c700a17ef835c72d3a5d7213f58cd23b52503a0; authautologin=86dd3279c3d6c32ba0d9a4e0bd1ee96a%7EBdkmBHTIg75R2UDSCvbygWHFc1wdbSff; is_authed=1")
                .asString();
        if (response.getStatus() != 200) {
            throw new IllegalArgumentException("Image not found with id: " + imageId);
        }
        String body = response.getBody();
        return new Gson().fromJson(body, ImgurlImageUploadResponseRoot.class);
    }
}


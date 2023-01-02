package com.synchrony.imageapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
    private Long id;
    private String imgurlImageId;
    private String title;
    private String description;
    private String type;
    private String link;
    private String accountUrl;
    private String accountId;
}

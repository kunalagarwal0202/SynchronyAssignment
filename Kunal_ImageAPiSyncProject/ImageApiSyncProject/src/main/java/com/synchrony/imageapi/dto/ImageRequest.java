package com.synchrony.imageapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageRequest {

    private String title;
    private String description;
    private String accountUrl;
    private String accountId;

}

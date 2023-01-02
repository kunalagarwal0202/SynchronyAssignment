package com.synchrony.imageapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgurlImageUploadResponseRoot {

    @JsonProperty("data")
    private ImgurlImageResponseData data;
    private boolean success;
    private int status;
}

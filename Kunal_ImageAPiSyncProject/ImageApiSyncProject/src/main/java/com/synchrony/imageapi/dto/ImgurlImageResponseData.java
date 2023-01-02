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
public class ImgurlImageResponseData {

    private  String id;
    private String title;
    private String description;
    private String type;
    private String link;
    @JsonProperty("account_url")
    private String accountUrl;
    @JsonProperty("account_id")
    private String accountId;

}

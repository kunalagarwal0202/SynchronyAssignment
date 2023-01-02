package com.synchrony.imageapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SynchronyUserDetails {

    private Long id;
    private String username;
    private String mobileNumber;
}

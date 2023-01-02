package com.synchrony.imageapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SynchronyUserDetailsRequest {

    private String username;
    private String password;
    private String mobileNumber;
}

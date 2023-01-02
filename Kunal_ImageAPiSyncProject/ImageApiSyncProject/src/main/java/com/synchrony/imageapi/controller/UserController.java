package com.synchrony.imageapi.controller;

import com.synchrony.imageapi.dto.SynchronyUserDetails;
import com.synchrony.imageapi.dto.SynchronyUserDetailsRequest;
import com.synchrony.imageapi.service.SynchronyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final SynchronyUserDetailsService userDetailsService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SynchronyUserDetailsRequest userDetailsRequest) {
        userDetailsService.registerUser(userDetailsRequest);
    }

    @GetMapping("profile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public SynchronyUserDetails getUserProfile(@PathVariable("username") String username) {
        return userDetailsService.getUserProfile(username);
    }
}

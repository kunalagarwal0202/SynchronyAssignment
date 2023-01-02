package com.synchrony.imageapi.service;

import com.synchrony.imageapi.dto.SynchronyUserDetails;
import com.synchrony.imageapi.dto.SynchronyUserDetailsRequest;
import com.synchrony.imageapi.entity.User;
import com.synchrony.imageapi.repository.SynchronyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SynchronyUserDetailsService implements UserDetailsService {

    private final SynchronyUserRepository synchronyUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = synchronyUserRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()).password(user.getPassword()).roles("write").build();
    }

    public void registerUser(SynchronyUserDetailsRequest userDetailsRequest) {
        User userEntity = User.builder()
                .username(userDetailsRequest.getUsername())
                .password(userDetailsRequest.getPassword())
                .mobilenumber(userDetailsRequest.getMobileNumber())
                .build();
        synchronyUserRepository.save(userEntity);
    }

    public SynchronyUserDetails getUserProfile(String username) {
        User user = synchronyUserRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username " + username);
        }
        return SynchronyUserDetails.builder()
                .mobileNumber(user.getMobilenumber())
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }
}

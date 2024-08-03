package com.zenthrex.security.dto;


import com.zenthrex.core.entites.User;

public record AuthenticationResponse(String accessToken, String refreshToken, User account) {}

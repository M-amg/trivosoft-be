package com.zenthrex.security.dto;

import com.zenthrex.core.enums.RoleEnum;

public record RegisterRequest(String firstname,String lastname, String email, String phone, String password, RoleEnum role) {}

package com.zenthrex.security.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenthrex.core.entites.Token;
import com.zenthrex.core.repositories.TokenRepository;
import com.zenthrex.core.repositories.UserRepository;
import com.zenthrex.security.model.Account;
import com.zenthrex.security.dto.AuthenticationRequest;
import com.zenthrex.security.dto.AuthenticationResponse;
import com.zenthrex.security.dto.RegisterRequest;
import com.zenthrex.core.enums.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository accountRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user =(Account)
                Account.builder()
                        .firstname(request.firstname())
                        .lastname(request.lastname())
                        .email(request.email())
                        .phone(request.phone())
                        .password(passwordEncoder.encode(request.password()))
                        .role(request.role())
                        .build();
        var savedUser = accountRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken, savedUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = (Account) accountRepository.findByEmail(request.email()).orElseThrow();
        log.info("user loaded {}", user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken, user);
    }

    private void saveUserToken(Account user, String jwtToken) {
        var token =
                Token.builder()
                        .user(user)
                        .token(jwtToken)
                        .tokenType(TokenType.BEARER)
                        .expired(false)
                        .revoked(false)
                        .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Account user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(
                token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            // todo custom add exception
            var user = (Account) this.accountRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = new AuthenticationResponse(accessToken, refreshToken, user);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}

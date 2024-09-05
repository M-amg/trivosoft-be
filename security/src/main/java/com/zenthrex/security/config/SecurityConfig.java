package com.zenthrex.security.config;

import com.zenthrex.security.filter.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.zenthrex.core.enums.RoleEnum.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(
                        cors ->
                                cors.configurationSource(request -> this.corsConfiguration()))
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/api/v1/auth/**").permitAll();
                            auth.requestMatchers("/api/v1/sellers/**").hasAnyRole(SELLER.name(),ADMIN.name());
                            auth.requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name());
                            auth.requestMatchers("/api/v1/buyers/**").hasAnyRole(BUYER.name(),SELLER.name(),ADMIN.name());
                            auth.anyRequest().permitAll();
                        })
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/api/v1/auth/logout")
                                        .addLogoutHandler(logoutHandler)
                                        .logoutSuccessHandler(
                                                (request, response, authentication) ->
                                                        SecurityContextHolder.clearContext()))
                .build();
    }

    private CorsConfiguration corsConfiguration() {
        // DEFINE AUTHORIZED IP
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        return configuration;
    }
}

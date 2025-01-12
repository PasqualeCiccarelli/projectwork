package com.example.projectwork.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.projectwork.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usa BCryptPasswordEncoder per la gestione della password
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**"))  // Ignora CSRF per le API REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/status").permitAll()  // Permette a tutti l'accesso all'endpoint di stato
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Permette solo agli utenti con ruolo ADMIN di accedere a /admin/**
                .requestMatchers("/user/**").hasRole("USER")  // Permette solo agli utenti con ruolo USER di accedere a /user/**
                .anyRequest().permitAll())  // Permette l'accesso a tutte le altre richieste
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Disabilita la gestione delle sessioni (stateless)

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());  // Usa il passwordEncoder per le operazioni di autenticazione

        return authenticationManagerBuilder.build();
    }
}
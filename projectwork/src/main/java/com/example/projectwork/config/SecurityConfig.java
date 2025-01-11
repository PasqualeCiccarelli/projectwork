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
	
	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())  // Disabilita la protezione CSRF
	        .formLogin(formLogin -> formLogin
	            .loginPage("/login")  // URL della tua pagina di login personalizzata
	            .permitAll())  // Permette l'accesso alla pagina di login a tutti
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/admin/**").hasRole("ADMIN")  // Richiede il ruolo 'ADMIN' per le rotte /admin/**
	            .requestMatchers("/user/**").hasRole("USER")  // Richiede il ruolo 'USER' per le rotte /user/**
	            .anyRequest().permitAll())  // Permette tutte le altre richieste
	        .sessionManagement(session -> 
	            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));  // Usa sessioni basate su cookie (stato della sessione)

	    return http.build();
	}

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

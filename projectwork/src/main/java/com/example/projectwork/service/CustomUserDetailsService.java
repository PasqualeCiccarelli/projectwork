package com.example.projectwork.service;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.UtenteRepository;
import com.example.projectwork.restCtrl.AuthController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private HttpServletRequest request;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca il cookie
        Cookie[] cookies = request.getCookies();
        String ruolo = null;

        if (cookies != null) {
            // Scorri i cookie per trovare "ruolo2"
            for (Cookie cookie : cookies) {
                if ("ruolo2".equals(cookie.getName())) {
                    ruolo = cookie.getValue();
                    break;
                }
            }
        }

        // Se non troviamo il cookie con ruolo2, fallisce
        if (ruolo == null) {
            throw new UsernameNotFoundException("Ruolo non trovato nel cookie per l'utente: " + username);
        }

        logger.info("Ruolo trovato nel cookie: {}", ruolo);

        // Crea la lista di autorità
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + ruolo));

        // Creazione dell'utente con il ruolo estratto dal cookie
        return new User(
            username,
            "",  // In un contesto di cookie non ti interessa la password
            authorities
        );
    }
}

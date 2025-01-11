package com.example.projectwork.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.UtenteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Prova a caricare prima l'utente dalla tabella degli utenti
        UtenteEntity utente = utenteRepository.findByEmail(username).orElse(null);

        // Se l'utente è presente nella tabella degli utenti, creiamo l'oggetto User
        if (utente != null) {
            // Creiamo l'oggetto User con il ruolo dell'utente
            return new User(
                utente.getEmail(), // username
                utente.getPassword(), // password
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utente.getRuolo().name())) // ruolo
            );
        }

        // Se l'utente non è stato trovato nella tabella degli utenti, cerchiamo nella tabella degli admin
        AdminEntity admin = adminRepository.findByEmail(username).orElse(null);

        // Se l'admin è presente nella tabella degli admin, creiamo l'oggetto User
        if (admin != null) {
            // Creiamo l'oggetto User con il ruolo dell'admin
            return new User(
                admin.getEmail(), // username
                admin.getPassword(), // password
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + admin.getRuolo().name())) // ruolo
            );
        }

        // Se l'utente o l'admin non sono stati trovati, solleviamo un'eccezione
        throw new UsernameNotFoundException("Utente con username " + username + " non trovato.");
    }
}

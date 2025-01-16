package com.example.projectwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.repository.UtenteRepository;
import com.example.projectwork.service.interf.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{

	@Autowired
	private UtenteRepository utenteRepository;

	public String aggiornaImmagineProfilo(String email, String nuovaImmagine) {
		UtenteEntity utente = utenteRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Utente con email " + email + " non trovato"));

		utente.setImmagine(nuovaImmagine);

		utenteRepository.save(utente);

		return "Immagine aggiornata con successo!";
	}
	
	public UtenteEntity getUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}

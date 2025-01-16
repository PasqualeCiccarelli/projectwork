package com.example.projectwork.service.interf;

import com.example.projectwork.entity.UtenteEntity;

public interface UtenteService {
	
	public String aggiornaImmagineProfilo(String email, String nuovaImmagine);
	public UtenteEntity getUtenteByEmail(String email);

}

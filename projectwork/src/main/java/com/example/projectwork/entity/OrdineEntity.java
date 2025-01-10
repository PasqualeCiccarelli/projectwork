package com.example.projectwork.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.projectwork.entity.entityenum.Stato;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class OrdineEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
	private UtenteEntity utente;
	
	private String indirizzo;
	
	private LocalDate data;
	
	private Stato stato_consegna;
	
	@OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
	private List<DettaglioOrdineEntity> dettagliOrdine;
	
	
	
	

}

package com.example.projectwork.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.example.projectwork.entity.entityenum.Ruolo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti")
public class UtenteEntity {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 100)
	private String cognome;
	
	@Column(length = 100, nullable = false, unique = true) 
	private String email;
	
	@Column(length = 20, nullable = false) 
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('UTENTE')")
	private Ruolo ruolo;
	
	private Date data_nascita;
	
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<OrdineEntity> ordini;
	
}

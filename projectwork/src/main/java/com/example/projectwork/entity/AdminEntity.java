package com.example.projectwork.entity;

import java.sql.Date;
import java.util.List;

import com.example.projectwork.entity.entityenum.Ruolo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "admin")
public class AdminEntity {

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
	@Column(nullable = false, columnDefinition = "ENUM('ADMIN')")
	private Ruolo ruolo;
	
	private Date data_nascita;
	
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<CardEntity> carte;

	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<AccessoriEntity> accessori;
	
	
}

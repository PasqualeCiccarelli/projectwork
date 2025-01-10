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
	
	public AdminEntity() {
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(long id, String nome, String cognome, String email, String password, Ruolo ruolo,
			Date data_nascita, List<CardEntity> carte, List<AccessoriEntity> accessori) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.data_nascita = data_nascita;
		this.carte = carte;
		this.accessori = accessori;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}

	public List<CardEntity> getCarte() {
		return carte;
	}

	public void setCarte(List<CardEntity> carte) {
		this.carte = carte;
	}

	public List<AccessoriEntity> getAccessori() {
		return accessori;
	}

	public void setAccessori(List<AccessoriEntity> accessori) {
		this.accessori = accessori;
	}
	
	
	
	
}

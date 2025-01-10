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
	
	public UtenteEntity() {
		// TODO Auto-generated constructor stub
	}

	public UtenteEntity(long id, String nome, String cognome, String email, String password, Ruolo ruolo,
			Date data_nascita, List<OrdineEntity> ordini) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.data_nascita = data_nascita;
		this.ordini = ordini;
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

	public List<OrdineEntity> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineEntity> ordini) {
		this.ordini = ordini;
	}
	
	
	
}

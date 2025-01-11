package com.example.projectwork.dto;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrazioneRequest {
	
	@NotNull(message = "Il nome é obbligatorio")
	private String nome;
	
	@NotNull(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotNull(message = "L'email è obbligatoria")
    @Email(message = "Formato dell'email non valido")
    private String email;

    @NotNull(message = "La password è obbligatoria")
    @Size(min = 8, message = "La password deve contenere almeno 8 caratteri")
    private String password;

    @NotNull(message = "La data di nascita è obbligatoria")
    private LocalDate dataNascita;
    
    @NotNull(message = "Accettare le politiche é obbligatrio")
    private boolean accettaPolitiche;

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

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public boolean isAdult() {
        return Period.between(this.dataNascita, LocalDate.now()).getYears() >= 16;
    }

	public boolean isAccettaPolitiche() {
		return accettaPolitiche;
	}

	public void setAccettaPolitiche(boolean accettaPolitiche) {
		this.accettaPolitiche = accettaPolitiche;
	}
    
    

}

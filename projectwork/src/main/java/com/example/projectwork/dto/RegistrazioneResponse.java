package com.example.projectwork.dto;

public class RegistrazioneResponse {

	private Long id;
	private String nome;
	private String email;
	private String ruolo1;
	private String ruolo2;

	public RegistrazioneResponse(Long id, String nome, String email, String ruolo1) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ruolo1 = ruolo1;
		this.ruolo2 = ruolo2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRuolo1() {
		return ruolo1;
	}

	public void setRuolo1(String ruolo1) {
		this.ruolo1 = ruolo1;
	}

	public String getRuolo2() {
		return ruolo2;
	}

	public void setRuolo2(String ruolo2) {
		this.ruolo2 = ruolo2;
	}

}

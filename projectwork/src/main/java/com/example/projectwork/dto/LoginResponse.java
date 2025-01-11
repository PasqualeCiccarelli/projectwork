package com.example.projectwork.dto;

public class LoginResponse {

	private String nome;
	private String ruolo1;
	private String ruolo2;
	private String email;
	private Long id;

	public LoginResponse(String nome, String ruolo1, String ruolo2, String email, Long id) {
		this.nome = nome;
		this.ruolo1 = ruolo1;
		this.ruolo2 = ruolo2;
		this.email = email;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

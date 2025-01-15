package com.example.projectwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RimuoviProdottoRequest{
	
	private String email;
    private Long dettaglioId;
    private int quantita;
    
    
    public RimuoviProdottoRequest() {
		// TODO Auto-generated constructor stub
	}


	public RimuoviProdottoRequest(String email, Long dettaglioId, int quantita) {
		super();
		this.email = email;
		this.dettaglioId = dettaglioId;
		this.quantita = quantita;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getDettaglioId() {
		return dettaglioId;
	}


	public void setDettaglioId(Long dettaglioId) {
		this.dettaglioId = dettaglioId;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
    
}

package com.example.projectwork.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggiungiProdottoRequest {

	 @NotNull
	 private String email;

	 @NotNull
	 private ProdottoDto prodotto;

	 @Min(1)
	 private int quantita;
    
    public AggiungiProdottoRequest() {
		// TODO Auto-generated constructor stub
	}

	public AggiungiProdottoRequest(String email, ProdottoDto prodotto, int quantita) {
		this.email = email;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProdottoDto getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDto prodotto) {
		this.prodotto = prodotto;
	}

}

package com.example.projectwork.dto;

import java.util.Arrays;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DettaglioOrdineRequest {

	@NotBlank(message = "Il tipo di prodotto è obbligatorio")
	private String tipoProdotto;

	@NotNull(message = "L'ID del prodotto non può essere nullo")
	private Long productId;

	@NotNull(message = "La quantità non può essere nulla")
	@Min(value = 1, message = "La quantità deve essere almeno 1")
	private Integer quantita;

	@NotBlank(message = "L'indirizzo è obbligatorio")
	private String indirizzo;

	public DettaglioOrdineRequest() {

	}

	public DettaglioOrdineRequest(String tipoProdotto, Long productId, Integer quantita, String indirizzo) {
		this.tipoProdotto = tipoProdotto;
		this.productId = productId;
		this.quantita = quantita;
		this.indirizzo = indirizzo;
	}

	// Getters e Setters
	public String getTipoProdotto() {
		return tipoProdotto;
	}

	public void setTipoProdotto(String tipoProdotto) {
		this.tipoProdotto = tipoProdotto;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		if (quantita <= 0) {
			throw new IllegalArgumentException("La quantità deve essere maggiore di zero");
		}
		this.quantita = quantita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void validate() {
		if (tipoProdotto == null || tipoProdotto.trim().isEmpty()) {
			throw new IllegalArgumentException("Il tipo prodotto è obbligatorio");
		}

		if (!Arrays.asList("CARTA", "BOX", "BUSTINA", "ACCESSORIO").contains(tipoProdotto.toUpperCase())) {
			throw new IllegalArgumentException("Tipo prodotto non valido");
		}

		if (productId == null) {
			throw new IllegalArgumentException("L'ID del prodotto è obbligatorio");
		}

		if (quantita == null || quantita <= 0) {
			throw new IllegalArgumentException("La quantità deve essere maggiore di zero");
		}
	}
}

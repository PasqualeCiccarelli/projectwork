package com.example.projectwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dettagli_ordine")
public class DettaglioOrdineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double prezzo;

	private Integer quantita;

	@ManyToOne
	@JoinColumn(name = "id_ordine", nullable = false)
	private OrdineEntity ordine;

	@ManyToOne
	@JoinColumn(name = "id_carta")
	private CardEntity carta;

	@ManyToOne
	@JoinColumn(name = "id_accessori")
	private AccessoriEntity accessorio;

	public DettaglioOrdineEntity() {
		// TODO Auto-generated constructor stub
	}

	public DettaglioOrdineEntity(Long id, Double prezzo, Integer quantita, OrdineEntity ordine, CardEntity carta,
			AccessoriEntity accessorio) {
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.ordine = ordine;
		this.carta = carta;
		this.accessorio = accessorio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public OrdineEntity getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineEntity ordine) {
		this.ordine = ordine;
	}

	public CardEntity getCarta() {
		return carta;
	}

	public void setCarta(CardEntity carta) {
		this.carta = carta;
	}

	public AccessoriEntity getAccessorio() {
		return accessorio;
	}

	public void setAccessorio(AccessoriEntity accessorio) {
		this.accessorio = accessorio;
	}

}

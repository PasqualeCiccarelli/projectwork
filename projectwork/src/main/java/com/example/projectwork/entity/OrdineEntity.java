package com.example.projectwork.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.projectwork.entity.entityenum.Stato;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class OrdineEntity extends ProdottoEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false)
	private UtenteEntity utente;

	private String indirizzo;

	private LocalDate data;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('ORDINATO', 'SPEDITO', 'IN_CONSEGNA', 'CONSEGNATO', 'IN_CORSO', 'ANNULLATO')")
	private Stato stato_consegna;

	@OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
	private List<DettaglioOrdineEntity> dettagliOrdine;

	public OrdineEntity() {
		
	}

	public OrdineEntity(Long id, UtenteEntity utente, String indirizzo, LocalDate data, Stato stato_consegna,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		this.id = id;
		this.utente = utente;
		this.indirizzo = indirizzo;
		this.data = data;
		this.stato_consegna = stato_consegna;
		this.dettagliOrdine = dettagliOrdine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UtenteEntity getUtente() {
		return utente;
	}

	public void setUtente(UtenteEntity utente) {
		this.utente = utente;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Stato getStato_consegna() {
		return stato_consegna;
	}

	public void setStato_consegna(Stato stato_consegna) {
		this.stato_consegna = stato_consegna;
	}

	public List<DettaglioOrdineEntity> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setDettagliOrdine(List<DettaglioOrdineEntity> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

}

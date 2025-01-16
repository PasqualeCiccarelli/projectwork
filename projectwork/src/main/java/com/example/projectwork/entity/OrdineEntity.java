package com.example.projectwork.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.projectwork.entity.entityenum.Stato;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordini")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    private UtenteEntity utente;

    @Column(length = 200)
    private String indirizzo;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Stato stato;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DettaglioOrdineEntity> dettagliOrdine;
    
    private double PrezzoTotale;


    public OrdineEntity() {

    }


	public OrdineEntity(Long id, UtenteEntity utente, String indirizzo, LocalDate data, Stato stato,
			List<DettaglioOrdineEntity> dettagliOrdine, double prezzoTotale) {
		super();
		this.id = id;
		this.utente = utente;
		this.indirizzo = indirizzo;
		this.data = data;
		this.stato = stato;
		this.dettagliOrdine = dettagliOrdine;
		PrezzoTotale = prezzoTotale;
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


	public Stato getStato() {
		return stato;
	}


	public void setStato(Stato stato) {
		this.stato = stato;
	}


	public List<DettaglioOrdineEntity> getDettagliOrdine() {
		return dettagliOrdine;
	}


	public void setDettagliOrdine(List<DettaglioOrdineEntity> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}


	public double getPrezzoTotale() {
		return PrezzoTotale;
	}


	public void setPrezzoTotale(double prezzoTotale) {
		PrezzoTotale = prezzoTotale;
	}

	
}

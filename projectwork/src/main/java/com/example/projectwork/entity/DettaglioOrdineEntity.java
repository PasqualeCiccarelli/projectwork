package com.example.projectwork.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dettagli_ordine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DettaglioOrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double prezzo;

    @Column(nullable = false)
    private Integer quantita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ordine", nullable = false)
    private OrdineEntity ordine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carta")
    private CardEntity carta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accessorio")
    private AccessorioEntity accessorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_box")
    private BoxEntity box;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bustina")
    private BustinaEntity bustina;

	public DettaglioOrdineEntity() {
		
	}

	public DettaglioOrdineEntity(Long id, Double prezzo, Integer quantita, OrdineEntity ordine, CardEntity carta,
			AccessorioEntity accessorio, BoxEntity box, BustinaEntity bustina) {
		super();
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.ordine = ordine;
		this.carta = carta;
		this.accessorio = accessorio;
		this.box = box;
		this.bustina = bustina;
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

	public AccessorioEntity getAccessorio() {
		return accessorio;
	}

	public void setAccessorio(AccessorioEntity accessorio) {
		this.accessorio = accessorio;
	}

	public BoxEntity getBox() {
		return box;
	}

	public void setBox(BoxEntity box) {
		this.box = box;
	}

	public BustinaEntity getBustina() {
		return bustina;
	}

	public void setBustina(BustinaEntity bustina) {
		this.bustina = bustina;
	}

	public ProdottoEntity getProdotto() {
	    if (this.carta != null) {
	        return this.carta;
	    } else if (this.box != null) {
	        return this.box;
	    } else if (this.bustina != null) {
	        return this.bustina;
	    } else if (this.accessorio != null) {
	        return this.accessorio;
	    } else {
	        throw new RuntimeException("Nessun prodotto associato a questo dettaglio d'ordine.");
	    }
	}

	public void setProdotto(ProdottoEntity prodotto) {
	    this.carta = null;
	    this.box = null;
	    this.bustina = null;
	    this.accessorio = null;

	    if (prodotto instanceof CardEntity) {
	        this.carta = (CardEntity) prodotto;
	    } else if (prodotto instanceof BoxEntity) {
	        this.box = (BoxEntity) prodotto;
	    } else if (prodotto instanceof BustinaEntity) {
	        this.bustina = (BustinaEntity) prodotto;
	    } else if (prodotto instanceof AccessorioEntity) {
	        this.accessorio = (AccessorioEntity) prodotto;
	    } else {
	        throw new IllegalArgumentException("Tipo di prodotto non valido: " + prodotto.getClass().getSimpleName());
	    }
	}

	

}

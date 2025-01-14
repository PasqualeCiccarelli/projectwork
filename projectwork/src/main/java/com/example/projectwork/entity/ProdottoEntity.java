package com.example.projectwork.entity;

import java.time.LocalDate;

import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProdottoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 100, nullable = false)
    private String immagine;

    @Column(length = 500)
    private String descrizione;

    @Column(nullable = false)
    private double prezzo;

    @Column
    private double prezzoScontato;

    @Column
    private int rimanenza;
    
    @Column
    private LocalDate DataInizio;

    @Column
    private boolean disponibilita;

    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    private Categoria categoria;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    private Tipo tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Brand brand;

    public ProdottoEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProdottoEntity(Long id, String nome, String immagine, String descrizione, double prezzo,
			double prezzoScontato, int rimanenza, LocalDate dataInizio, boolean disponibilita, Categoria categoria,
			Tipo tipo, Brand brand) {
		super();
		this.id = id;
		this.nome = nome;
		this.immagine = immagine;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.prezzoScontato = prezzoScontato;
		this.rimanenza = rimanenza;
		DataInizio = dataInizio;
		this.disponibilita = disponibilita;
		this.categoria = categoria;
		this.tipo = tipo;
		this.brand = brand;
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

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public double getPrezzoScontato() {
		return prezzoScontato;
	}

	public void setPrezzoScontato(double prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}

	public int getRimanenza() {
		return rimanenza;
	}

	public void setRimanenza(int rimanenza) {
		this.rimanenza = rimanenza;
	}

	public LocalDate getDataInizio() {
		return DataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		DataInizio = dataInizio;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public double getPrezzoFinale() {
		if(categoria.equals(Categoria.SPECIALE)) {
			return prezzoScontato;
		}else {
			return prezzo;
		}
	}

}
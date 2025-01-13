package com.example.projectwork.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

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
@Table(name = "bustine")
public class BustinaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 500, nullable = false)
	private String descrizione;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('PREVENDITA', 'NOVITA', 'DEFAULT', 'SPECIALE')")
	private Categoria categoria;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('POKEMON', 'MAGIC', 'YUGIHO')")
	private Brand brand;

	@Column
	private boolean disponibilita;

	@Column(nullable = false)
	private String immagine;

	@Column(nullable = false)
	private double prezzo;

	@Column
	private double prezzoScontato;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('EDIZIONE_LIMITATA', 'DEFAULT')")
	private Tipo tipo;

	@Column
	private LocalDate dataInizio;

	@Column
	private int rimanenza;
	
	@Column
	private int numero_carte;

	@ManyToOne
	@JoinColumn(name = "id_admin")
	private AdminEntity admin;

	@OneToMany(mappedBy = "bustina", cascade = CascadeType.ALL)
	private List<DettaglioOrdineEntity> dettagliOrdine;
	
	public BustinaEntity() {
		// TODO Auto-generated constructor stub
	}

	public BustinaEntity(Long id, String nome, String descrizione, Categoria categoria, Brand brand,
			boolean disponibilita, String immagine, double prezzo, double prezzoScontato, Tipo tipo,
			LocalDate dataInizio, int rimanenza, int numero_carte, AdminEntity admin,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.brand = brand;
		this.disponibilita = disponibilita;
		this.immagine = immagine;
		this.prezzo = prezzo;
		this.prezzoScontato = prezzoScontato;
		this.tipo = tipo;
		this.dataInizio = dataInizio;
		this.rimanenza = rimanenza;
		this.numero_carte = numero_carte;
		this.admin = admin;
		this.dettagliOrdine = dettagliOrdine;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public int getRimanenza() {
		return rimanenza;
	}

	public void setRimanenza(int rimanenza) {
		this.rimanenza = rimanenza;
	}

	public int getNumero_carte() {
		return numero_carte;
	}

	public void setNumero_carte(int numero_carte) {
		this.numero_carte = numero_carte;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}

	public List<DettaglioOrdineEntity> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setDettagliOrdine(List<DettaglioOrdineEntity> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

}

package com.example.projectwork.entity;

import java.time.LocalDate;
import java.util.List;

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
@Table(name= "accessori")
public class AccessoriEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length= 100, nullable=false)
	private String nome;

	@Column(length= 500, nullable=false)
	private String descrizione;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('PREVENTIDA', 'NOVITA', 'DEFAULT', 'SPECIALE')")
	private Categoria categoria;
	 
	@Column
	private boolean disponibilita;

	@Column(nullable=false)
	private String immagine;

	@Column(nullable=false)
	private double prezzo;

	@Column
	private double prezzoScontato;

	@Column
	private double peso;

	@Column
	private String dimensioni;
	 
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('DEFAULT', 'EDIZIONE_LIMITATA', 'ACTION_FIGURE', 'GADGET', 'RACCOGLITORI', 'SLEEVE')")
	private Tipo tipo;

	@Column
	private LocalDate dataInizio;
	
	@Column
	private int rimanenza;

	@ManyToOne
	@JoinColumn(name = "id_admin")
	private AdminEntity admin;

	@OneToMany(mappedBy = "accessori", cascade = CascadeType.ALL)
	private List<DettaglioOrdineEntity> dettagliOrdine;
	
	
	public AccessoriEntity() {
		
	}

	public AccessoriEntity(Long id, String nome, String descrizione, Categoria categoria, boolean disponibilita,
		String immagine, double prezzo, double prezzoScontato, double peso, String dimensioni, Tipo tipo,
		LocalDate dataInizio, int rimanenza, AdminEntity admin, List<DettaglioOrdineEntity> dettagliOrdine) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.disponibilita = disponibilita;
		this.immagine = immagine;
		this.prezzo = prezzo;
		this.prezzoScontato = prezzoScontato;
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.tipo = tipo;
		this.dataInizio = dataInizio;
		this.rimanenza = rimanenza;
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

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDimensioni() {
		return dimensioni;
	}

	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
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

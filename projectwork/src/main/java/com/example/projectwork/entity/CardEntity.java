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
@Table(name= "card")
public class CardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length= 100, nullable=false)
	private String nome;
	
	@Column(length= 500)
	private String Descrizione;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('PREVENTIDA', 'NOVITA', 'DEFAULT', 'SPECIALE')")
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('DEFAULT', 'EDIZIONE_LIMITATA')")
	private Tipo tipo;
	
	@Column(nullable=false)
	private double prezzo;
	
	@Column
	private int rimanenze;
	
	@Column
	private boolean disponibilita;
	
	@Column(nullable= false)
	private String immagine;
	
	@Column
	private LocalDate data_inizio;
	
	@Column
	private double prezzo_scontato;
	
	@ManyToOne
	@JoinColumn(name = "id_admin")
	private AdminEntity admin;

	@OneToMany(mappedBy = "carta", cascade = CascadeType.ALL)
	private List<DettaglioOrdineEntity> dettagli_ordine;
	 
	 
	
	
	public CardEntity() {
		
	};
	 
	 
	

	public CardEntity(long id, String nome, String descrizione, String categoria, double prezzo, int rimanenze,
		boolean disponibilita, String immagine, LocalDate data_inizio, double prezzo_scontato, AdminEntity admin,
		List<DettaglioOrdineEntity> dettagli_ordine) {
		super();
		this.id = id;
		this.nome = nome;
		Descrizione = descrizione;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.rimanenze = rimanenze;
		this.disponibilita = disponibilita;
		this.immagine = immagine;
		this.data_inizio = data_inizio;
		this.prezzo_scontato = prezzo_scontato;
		this.admin = admin;
		this.dettagli_ordine = dettagli_ordine;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getDescrizione() {
		return Descrizione;
	}




	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}




	public String getCategoria() {
		return categoria;
	}




	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}




	public double getPrezzo() {
		return prezzo;
	}




	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}




	public int getRimanenze() {
		return rimanenze;
	}




	public void setRimanenze(int rimanenze) {
		this.rimanenze = rimanenze;
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




	public LocalDate getData_inizio() {
		return data_inizio;
	}




	public void setData_inizio(LocalDate data_inizio) {
		this.data_inizio = data_inizio;
	}




	public double getPrezzo_scontato() {
		return prezzo_scontato;
	}




	public void setPrezzo_scontato(double prezzo_scontato) {
		this.prezzo_scontato = prezzo_scontato;
	}




	public AdminEntity getAdmin() {
		return admin;
	}




	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}




	public List<DettaglioOrdineEntity> getDettagli_ordine() {
		return dettagli_ordine;
	}




	public void setDettagli_ordine(List<DettaglioOrdineEntity> dettagli_ordine) {
		this.dettagli_ordine = dettagli_ordine;
	}
	
	
	
	
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	
}

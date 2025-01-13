package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public class CardDto {

	private Long id;
	private String nome;
	private String descrizione;
	private Categoria categoria;
	private Tipo tipo;
	private Brand brand;
	private double prezzo;
	private int rimanenze;
	private boolean disponibilita;
	private String immagine;
	private LocalDate dataInizio;
	private double prezzoScontato;
	private Long adminId;
	private List<DettagliOrdineDto> dettagliOrdine;

	public CardDto() {
		
	}

	public CardDto(Long id, String nome, String descrizione, Categoria categoria, Tipo tipo, Brand brand, double prezzo,
			int rimanenze, boolean disponibilita, String immagine, LocalDate dataInizio, double prezzoScontato,
			Long adminId, List<DettagliOrdineDto> dettagliOrdine) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.tipo = tipo;
		this.brand = brand;
		this.prezzo = prezzo;
		this.rimanenze = rimanenze;
		this.disponibilita = disponibilita;
		this.immagine = immagine;
		this.dataInizio = dataInizio;
		this.prezzoScontato = prezzoScontato;
		this.adminId = adminId;
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

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public double getPrezzoScontato() {
		return prezzoScontato;
	}

	public void setPrezzoScontato(double prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public List<DettagliOrdineDto> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setDettagliOrdine(List<DettagliOrdineDto> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

	public static CardDto fromEntity(CardEntity entity) {
		if (entity == null)
			return null;

		CardDto dto = new CardDto();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescrizione(entity.getDescrizione());
		dto.setCategoria(entity.getCategoria());
		dto.setTipo(entity.getTipo());
		dto.setBrand(entity.getBrand());
		dto.setPrezzo(entity.getPrezzo());
		dto.setRimanenze(entity.getRimanenze());
		dto.setDisponibilita(entity.isDisponibilita());
		dto.setImmagine(entity.getImmagine());
		dto.setDataInizio(entity.getData_inizio());
		dto.setPrezzoScontato(entity.getPrezzo_scontato());
		dto.setAdminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null);

		if (entity.getDettagli_ordine() != null) {
			dto.setDettagliOrdine(entity.getDettagli_ordine().stream().map(DettagliOrdineDto::fromEntity)
					.collect(Collectors.toList()));
		}

		return dto;
	}

	public CardEntity toEntity(CardEntity existingEntity) {
		CardEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity : new CardEntity();

		entity.setNome(this.getNome());
		entity.setDescrizione(this.getDescrizione());
		entity.setCategoria(this.getCategoria());
		entity.setTipo(this.getTipo());
		entity.setBrand(this.getBrand());
		entity.setPrezzo(this.getPrezzo());
		entity.setRimanenze(this.getRimanenze());
		entity.setDisponibilita(this.isDisponibilita());
		entity.setImmagine(this.getImmagine());
		entity.setData_inizio(this.getDataInizio());
		entity.setPrezzo_scontato(this.getPrezzoScontato());

		if (this.getAdminId() != null) {
			AdminEntity adminEntity = new AdminEntity();
			adminEntity.setId(this.getAdminId());
			entity.setAdmin(adminEntity);
		}

		if (this.getDettagliOrdine() != null) {
			List<DettaglioOrdineEntity> dettagliOrdine = this.getDettagliOrdine().stream()
					.map(dto -> dto.toEntity(null)).collect(Collectors.toList());
			entity.setDettagli_ordine(dettagliOrdine);
		}

		return entity;
	}

}

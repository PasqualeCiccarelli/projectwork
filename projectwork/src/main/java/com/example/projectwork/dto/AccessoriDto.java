package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public class AccessoriDto {
	private Long id;
	private String nome;
	private String descrizione;
	private Categoria categoria;
	private Brand brand;
	private boolean disponibilita;
	private String immagine;
	private double prezzo;
	private double prezzoScontato;
	private double peso;
	private String dimensioni;
	private Tipo tipo;
	private LocalDate dataInizio;
	private int rimanenza;
	private Long adminId;
	private List<DettagliOrdineDto> dettagliOrdine;

	public AccessoriDto() {
		// TODO Auto-generated constructor stub
	}

	public AccessoriDto(Long id, String nome, String descrizione, Categoria categoria, Brand brand,
			boolean disponibilita, String immagine, double prezzo, double prezzoScontato, double peso,
			String dimensioni, Tipo tipo, LocalDate dataInizio, int rimanenza, Long adminId,
			List<DettagliOrdineDto> dettagliOrdine) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.brand = brand;
		this.disponibilita = disponibilita;
		this.immagine = immagine;
		this.prezzo = prezzo;
		this.prezzoScontato = prezzoScontato;
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.tipo = tipo;
		this.dataInizio = dataInizio;
		this.rimanenza = rimanenza;
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

	public static AccessoriDto fromEntity(AccessoriEntity entity) {
		if (entity == null)
			return null;

		AccessoriDto dto = new AccessoriDto();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescrizione(entity.getDescrizione());
		dto.setCategoria(entity.getCategoria());
		dto.setBrand(entity.getBrand());
		dto.setDisponibilita(entity.isDisponibilita());
		dto.setImmagine(entity.getImmagine());
		dto.setPrezzo(entity.getPrezzo());
		dto.setPrezzoScontato(entity.getPrezzoScontato());
		dto.setPeso(entity.getPeso());
		dto.setDimensioni(entity.getDimensioni());
		dto.setTipo(entity.getTipo());
		dto.setDataInizio(entity.getDataInizio());
		dto.setRimanenza(entity.getRimanenza());
		dto.setAdminId(entity.getAdmin() != null ? entity.getAdmin().getId() : null);

		if (entity.getDettagliOrdine() != null) {
			dto.setDettagliOrdine(entity.getDettagliOrdine().stream().map(DettagliOrdineDto::fromEntity)
					.collect(Collectors.toList()));
		}
		return dto;
	}

	public AccessoriEntity toEntity(AccessoriEntity existingEntity) {
		AccessoriEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity
				: new AccessoriEntity();

		entity.setNome(this.getNome());
		entity.setDescrizione(this.getDescrizione());
		entity.setCategoria(this.getCategoria());
		entity.setBrand(this.getBrand());
		entity.setDisponibilita(this.isDisponibilita());
		entity.setImmagine(this.getImmagine());
		entity.setPrezzo(this.getPrezzo());
		entity.setPrezzoScontato(this.getPrezzoScontato());
		entity.setPeso(this.getPeso());
		entity.setDimensioni(this.getDimensioni());
		entity.setTipo(this.getTipo());
		entity.setDataInizio(this.getDataInizio());
		entity.setRimanenza(this.getRimanenza());

		if (this.getAdminId() != null) {
			AdminEntity adminEntity = new AdminEntity();
			adminEntity.setId(this.getAdminId());
			entity.setAdmin(adminEntity);
		}

		if (this.getDettagliOrdine() != null) {
			List<DettaglioOrdineEntity> dettagliOrdine = this.getDettagliOrdine().stream()
					.map(dto -> dto.toEntity(null)).collect(Collectors.toList());
			entity.setDettagliOrdine(dettagliOrdine);
		}

		return entity;
	}

}

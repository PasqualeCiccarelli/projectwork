package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projectwork.entity.AccessorioEntity;
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

	    public AccessoriDto() {}

	    public static AccessoriDto fromEntity(AccessorioEntity entity) {
	        if (entity == null) {
	            return null;
	        }

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

	        if (entity.getAdmin() != null) {
	            dto.setAdminId(entity.getAdmin().getId());
	        }

	        if (entity.getDettagliOrdine() != null) {
	            dto.setDettagliOrdine(entity.getDettagliOrdine().stream()
	                    .map(DettagliOrdineDto::fromEntity)
	                    .collect(Collectors.toList()));
	        }

	        return dto;
	    }

	    public AccessorioEntity toEntity(AccessorioEntity existingEntity) {
	        AccessorioEntity entity = (existingEntity != null && this.getId() != null)
	                ? existingEntity
	                : new AccessorioEntity();

	        entity.setNome(this.getNome());
	        entity.setDescrizione(this.getDescrizione());
	        entity.setCategoria(this.getCategoria());
	        entity.setBrand(this.getBrand());
	        entity.setDisponibilita(this.isDisponibilita());
	        entity.setImmagine(this.getImmagine());
	        entity.setPrezzo(this.getPrezzo());
	        entity.setPrezzoScontato(this.getPrezzoScontato());
	        entity.setPeso(this.getPeso());
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
	                    .map(dto -> dto.toEntity(null))
	                    .collect(Collectors.toList());
	            entity.setDettagliOrdine(dettagliOrdine);
	        }

	        return entity;
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
}

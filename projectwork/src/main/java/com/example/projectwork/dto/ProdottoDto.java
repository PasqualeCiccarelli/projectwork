package com.example.projectwork.dto;

import java.time.LocalDate;

import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoDto {

	    private Long id;
	    private String nome;
	    private String immagine;
	    private String descrizione;
	    private double prezzo;
	    private double prezzoScontato;
	    private int rimanenza;
	    private LocalDate dataInizio;
	    private boolean disponibilita;
	    private Categoria categoria;
	    private Tipo tipo;
	    private Brand brand;
	    private String specificDetails;
	    
	    public ProdottoDto() {
			// TODO Auto-generated constructor stub
		}
	    
		public ProdottoDto(Long id, String nome, String immagine, String descrizione, double prezzo,
				double prezzoScontato, int rimanenza, LocalDate dataInizio, boolean disponibilita, Categoria categoria,
				Tipo tipo, Brand brand, String specificDetails) {
			super();
			this.id = id;
			this.nome = nome;
			this.immagine = immagine;
			this.descrizione = descrizione;
			this.prezzo = prezzo;
			this.prezzoScontato = prezzoScontato;
			this.rimanenza = rimanenza;
			this.dataInizio = dataInizio;
			this.disponibilita = disponibilita;
			this.categoria = categoria;
			this.tipo = tipo;
			this.brand = brand;
			this.specificDetails = specificDetails;
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
			return dataInizio;
		}

		public void setDataInizio(LocalDate dataInizio) {
			this.dataInizio = dataInizio;
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

		public String getSpecificDetails() {
			return specificDetails;
		}

		public void setSpecificDetails(String specificDetails) {
			this.specificDetails = specificDetails;
		}

		public ProdottoDto toDto(ProdottoEntity prodotto) {
	        ProdottoDto dto = new ProdottoDto();

	        dto.setId(prodotto.getId());
	        dto.setNome(prodotto.getNome());
	        dto.setImmagine(prodotto.getImmagine());
	        dto.setDescrizione(prodotto.getDescrizione());
	        dto.setPrezzo(prodotto.getPrezzo());
	        dto.setPrezzoScontato(prodotto.getPrezzoScontato());
	        dto.setRimanenza(prodotto.getRimanenza());
	        dto.setDataInizio(prodotto.getDataInizio());
	        dto.setDisponibilita(prodotto.isDisponibilita());
	        dto.setCategoria(prodotto.getCategoria());
	        dto.setTipo(prodotto.getTipo());
	        dto.setBrand(prodotto.getBrand());

	        if (prodotto instanceof CardEntity) {
	            CardEntity card = (CardEntity) prodotto;
	            dto.setSpecificDetails("Edizione: " + card.getEdizione() + ", Rarità: " + card.getRarita());
	        } else if (prodotto instanceof BoxEntity) {
	            BoxEntity box = (BoxEntity) prodotto;
	            dto.setSpecificDetails("Numero Bustine: " + box.getNumeroBustine() + ", Quantità Carte: " + box.getQuantitaCarte());
	        } else if (prodotto instanceof BustinaEntity) {
	            BustinaEntity bustina = (BustinaEntity) prodotto;
	            dto.setSpecificDetails("Quantità Carte: " + bustina.getQuantitaCarte());
	        } else if (prodotto instanceof AccessorioEntity) {
	            AccessorioEntity accessorio = (AccessorioEntity) prodotto;
	            dto.setSpecificDetails("Colore: " + accessorio.getColore() + ", Peso: " + accessorio.getPeso() + ", Dimensioni: " + accessorio.getDimensioni());
	        }

	        return dto;
	    }

}

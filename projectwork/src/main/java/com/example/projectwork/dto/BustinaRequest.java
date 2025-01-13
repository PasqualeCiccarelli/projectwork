package com.example.projectwork.dto;

import java.time.LocalDate;

import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;

public class BustinaRequest {
	
	private String nome;
    private String descrizione;
    private Categoria categoria;
    private Tipo tipo;
    private Brand brand;
    private double prezzo;
    private int rimanenze;
    private boolean disponibilita;
    private String immagine;
    private LocalDate data_inizio;
    private double prezzo_scontato;
    private String emailAdmin;
    private int numero_carte;
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
	public String getEmailAdmin() {
		return emailAdmin;
	}
	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}
	public int getNumero_carte() {
		return numero_carte;
	}
	public void setNumero_carte(int numero_carte) {
		this.numero_carte = numero_carte;
	}
    
    

}

package com.example.projectwork.entity;

import com.example.projectwork.entity.entityenum.Categoria;

public abstract class ProdottoEntity {
	
	private Long id;
    private String nome;
    private double prezzo;
    private double prezzoScontato;
    private int rimanenza;
    private boolean disponibilita;
    private Categoria categoria;
    
    public ProdottoEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProdottoEntity(Long id, String nome, double prezzo, double prezzoScontato, int rimanenza,
			boolean disponibilita, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.prezzoScontato = prezzoScontato;
		this.rimanenza = rimanenza;
		this.disponibilita = disponibilita;
		this.categoria = categoria;
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

	public double getPrezzoFinale() {
        if(categoria == Categoria.SPECIALE) {
        	return prezzoScontato;
        }else {
        	return prezzo;
        }
    }
    
    

}

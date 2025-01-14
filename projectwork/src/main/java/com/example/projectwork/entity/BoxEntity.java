package com.example.projectwork.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoxEntity extends ProdottoEntity {

    @Column(nullable = false)
    private int numeroBustine;

    @Column(nullable = false)
    private int quantitaCarte;

    @Column(nullable = false, length = 100)
    private String nomeSet;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminEntity admin;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
    private List<DettaglioOrdineEntity> dettagliOrdine;

    
    public BoxEntity() {
		// TODO Auto-generated constructor stub
	}

	public BoxEntity(int numeroBustine, int quantitaCarte, String nomeSet, AdminEntity admin,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		super();
		this.numeroBustine = numeroBustine;
		this.quantitaCarte = quantitaCarte;
		this.nomeSet = nomeSet;
		this.admin = admin;
		this.dettagliOrdine = dettagliOrdine;
	}

	public int getNumeroBustine() {
		return numeroBustine;
	}

	public void setNumeroBustine(int numeroBustine) {
		this.numeroBustine = numeroBustine;
	}

	public int getQuantitaCarte() {
		return quantitaCarte;
	}

	public void setQuantitaCarte(int quantitaCarte) {
		this.quantitaCarte = quantitaCarte;
	}

	public String getNomeSet() {
		return nomeSet;
	}

	public void setNomeSet(String set) {
		this.nomeSet = set;
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

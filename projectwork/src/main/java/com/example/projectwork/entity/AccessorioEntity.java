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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessorioEntity extends ProdottoEntity {

    @Column( length = 50)
    private String colore;
    
    @Column
    private double peso;
    
    @Column(length = 500)
    private String dimensioni;
    
    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminEntity admin;

    @OneToMany(mappedBy = "accessorio", cascade = CascadeType.ALL)
    private List<DettaglioOrdineEntity> dettagliOrdine;
    
    public AccessorioEntity() {
	
	}

	public AccessorioEntity(String colore, double peso, String dimensioni, AdminEntity admin,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		super();
		this.colore = colore;
		this.peso = peso;
		this.dimensioni = dimensioni;
		this.admin = admin;
		this.dettagliOrdine = dettagliOrdine;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
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

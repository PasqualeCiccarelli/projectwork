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
public class BustinaEntity extends ProdottoEntity {

    @Column(nullable = false)
    private int quantitaCarte;

    @Column(length = 100)
    private String nomeSet;
    
    @Column(length = 50)
    private String edizione;
    
    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminEntity admin;

    @OneToMany(mappedBy = "bustina", cascade = CascadeType.ALL)
    private List<DettaglioOrdineEntity> dettagliOrdine;

    public BustinaEntity() {
		// TODO Auto-generated constructor stub
	}

	public BustinaEntity(int quantitaCarte, String nomeSet, String edizione, AdminEntity admin,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		super();
		this.quantitaCarte = quantitaCarte;
		this.nomeSet = nomeSet;
		this.edizione = edizione;
		this.admin = admin;
		this.dettagliOrdine = dettagliOrdine;
	}


	public String getEdizione() {
		return edizione;
	}

	public void setEdizione(String edizione) {
		this.edizione = edizione;
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

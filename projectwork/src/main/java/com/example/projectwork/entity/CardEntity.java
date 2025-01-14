package com.example.projectwork.entity;


import java.util.List;
import com.example.projectwork.entity.entityenum.Rarita;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CardEntity extends ProdottoEntity {

    @Column(length = 50)
    private String edizione;

    @Enumerated(EnumType.STRING)
    @Column
    private Rarita rarita;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdminEntity admin;
    
    @Column(length = 100)
    private String nomeSet;

    @OneToMany(mappedBy = "carta", cascade = CascadeType.ALL)
    private List<DettaglioOrdineEntity> dettagliOrdine;


	public CardEntity() {

	}


	public CardEntity(String edizione, Rarita rarita, String nomeSet, AdminEntity admin,
			List<DettaglioOrdineEntity> dettagliOrdine) {
		super();
		this.edizione = edizione;
		this.rarita = rarita;
		this.nomeSet = nomeSet;
		this.admin = admin;
		this.dettagliOrdine = dettagliOrdine;
	}


	public String getEdizione() {
		return edizione;
	}


	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}


	public Rarita getRarita() {
		return rarita;
	}


	public void setRarita(Rarita rarita) {
		this.rarita = rarita;
	}

	public String getNomeSet() {
		return nomeSet;
	}


	public void setNomeSet(String nomeSet) {
		this.nomeSet = nomeSet;
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

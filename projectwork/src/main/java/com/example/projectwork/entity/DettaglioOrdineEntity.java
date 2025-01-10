package com.example.projectwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "dettagli_ordine")
public class DettaglioOrdineEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private Double prezzo;

	 private Integer quantita;

	 @ManyToOne
	 @JoinColumn(name = "id_ordine", nullable = false)
	 private OrdineEntity ordine;

	 @ManyToOne
	 @JoinColumn(name = "id_carta")
	 private CardEntity carta;

	 @ManyToOne
	 @JoinColumn(name = "id_accessori")
	 private AccessoriEntity accessorio;

}

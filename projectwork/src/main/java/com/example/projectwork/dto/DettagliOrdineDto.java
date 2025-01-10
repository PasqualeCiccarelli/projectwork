package com.example.projectwork.dto;

import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;

public class DettagliOrdineDto {
	private Long id;
	private double prezzo;
	private int quantita;
	private Long ordineId;
	private Long cartaId;
	private Long accessorioId;

	public DettagliOrdineDto() {
		// TODO Auto-generated constructor stub
	}

	public DettagliOrdineDto(Long id, double prezzo, int quantita, Long ordineId, Long cartaId, Long accessorioId) {
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.ordineId = ordineId;
		this.cartaId = cartaId;
		this.accessorioId = accessorioId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Long getOrdineId() {
		return ordineId;
	}

	public void setOrdineId(Long ordineId) {
		this.ordineId = ordineId;
	}

	public Long getCartaId() {
		return cartaId;
	}

	public void setCartaId(Long cartaId) {
		this.cartaId = cartaId;
	}

	public Long getAccessorioId() {
		return accessorioId;
	}

	public void setAccessorioId(Long accessorioId) {
		this.accessorioId = accessorioId;
	}

	public static DettagliOrdineDto fromEntity(DettaglioOrdineEntity entity) {
		DettagliOrdineDto dto = new DettagliOrdineDto();
		dto.setId(entity.getId());
		dto.setPrezzo(entity.getPrezzo());
		dto.setQuantita(entity.getQuantita());
		dto.setOrdineId(entity.getOrdine().getId());
		dto.setCartaId(entity.getCarta() != null ? entity.getCarta().getId() : null);
		dto.setAccessorioId(entity.getAccessorio() != null ? entity.getAccessorio().getId() : null);
		return dto;
	}

	public DettaglioOrdineEntity toEntity(DettaglioOrdineEntity existingEntity) {
		DettaglioOrdineEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity
				: new DettaglioOrdineEntity();

		entity.setPrezzo(this.getPrezzo());
		entity.setQuantita(this.getQuantita());

		if (this.getOrdineId() != null) {
			OrdineEntity ordineEntity = new OrdineEntity();
			ordineEntity.setId(this.getOrdineId());
			entity.setOrdine(ordineEntity);
		}

		if (this.getCartaId() != null) {
			CardEntity cartaEntity = new CardEntity();
			cartaEntity.setId(this.getCartaId());
			entity.setCarta(cartaEntity);
		}

		if (this.getAccessorioId() != null) {
			AccessoriEntity accessorioEntity = new AccessoriEntity();
			accessorioEntity.setId(this.getAccessorioId());
			entity.setAccessorio(accessorioEntity);
		}

		return entity;
	}

}

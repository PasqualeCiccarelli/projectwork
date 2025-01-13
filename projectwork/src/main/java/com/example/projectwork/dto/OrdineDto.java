package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Stato;

public class OrdineDto {
	private Long id;
    private long utenteId;
    private String indirizzo;
    private LocalDate data;
    private Stato stato;
    private List<DettagliOrdineDto> dettagliOrdine;

    public OrdineDto() {

    }

    public OrdineDto(Long id, long utenteId, String indirizzo, LocalDate data, Stato stato,
            List<DettagliOrdineDto> dettagliOrdine) {
        this.id = id;
        this.utenteId = utenteId;
        this.indirizzo = indirizzo;
        this.data = data;
        this.stato = stato;
        this.dettagliOrdine = dettagliOrdine;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(long utenteId) {
		this.utenteId = utenteId;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public List<DettagliOrdineDto> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setDettagliOrdine(List<DettagliOrdineDto> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

	public static OrdineDto fromEntity(OrdineEntity entity) {
        OrdineDto dto = new OrdineDto();
        dto.setId(entity.getId());
        dto.setUtenteId(entity.getUtente().getId());
        dto.setIndirizzo(entity.getIndirizzo());
        dto.setData(entity.getData());
        dto.setStato(entity.getStato());
        dto.setDettagliOrdine(
                entity.getDettagliOrdine().stream().map(DettagliOrdineDto::fromEntity).collect(Collectors.toList()));
        return dto;
    }

    public OrdineEntity toEntity(OrdineEntity existingEntity) {
        OrdineEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity : new OrdineEntity();

        entity.setIndirizzo(this.getIndirizzo());
        entity.setData(this.getData());
        entity.setStato(this.getStato());

        if (this.getUtenteId() != 0) {
            UtenteEntity utenteEntity = new UtenteEntity();
            utenteEntity.setId(this.getUtenteId());
            entity.setUtente(utenteEntity);
        }

        if (this.getDettagliOrdine() != null) {
            List<DettaglioOrdineEntity> dettaglioOrdineEntities = this.getDettagliOrdine().stream()
                    .map(dettagliOrdineDto -> dettagliOrdineDto.toEntity(null)).collect(Collectors.toList());
            entity.setDettagliOrdine(dettaglioOrdineEntities);
        }

        return entity;
    }
}

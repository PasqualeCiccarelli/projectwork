package com.example.projectwork.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Stato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdineDto {
    private Long id;
    private long utenteId;
    private String indirizzo;
    private LocalDate data;
    private Stato stato;
    private List<DettagliOrdineDto> dettagliOrdine;
    private double prezzoTotale;

    public OrdineDto() {

    }

    public OrdineDto(Long id, long utenteId, String indirizzo, LocalDate data, Stato stato,
			List<DettagliOrdineDto> dettagliOrdine, double prezzoTotale) {
		super();
		this.id = id;
		this.utenteId = utenteId;
		this.indirizzo = indirizzo;
		this.data = data;
		this.stato = stato;
		this.dettagliOrdine = dettagliOrdine;
		this.prezzoTotale = prezzoTotale;
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

	public double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public static OrdineDto fromEntity(OrdineEntity entity) {
        if (entity == null) {
            return null;
        }

        OrdineDto dto = new OrdineDto();
        dto.setId(entity.getId());
        dto.setUtenteId(entity.getUtente().getId());
        dto.setIndirizzo(entity.getIndirizzo());
        dto.setData(entity.getData());
        dto.setStato(entity.getStato());
        
        if (entity.getDettagliOrdine() != null) {
            List<DettagliOrdineDto> dettagli = entity.getDettagliOrdine().stream()
                .map(DettagliOrdineDto::fromEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            dto.setDettagliOrdine(dettagli);

            double totale = dettagli.stream()
                .mapToDouble(d -> d.getPrezzo() * d.getQuantita())
                .sum();
            dto.setPrezzoTotale(totale);
        } else {
            dto.setDettagliOrdine(new ArrayList<>());
            dto.setPrezzoTotale(0.0);
        }

        return dto;
    }

    public OrdineEntity toEntity(OrdineEntity existingEntity) {
        OrdineEntity entity = (existingEntity != null && this.getId() != null) ? 
            existingEntity : new OrdineEntity();

        if (this.getIndirizzo() != null) {
            entity.setIndirizzo(this.getIndirizzo());
        }
        
        if (this.getData() != null) {
            entity.setData(this.getData());
        } else {
            entity.setData(LocalDate.now());
        }
        
        if (this.getStato() != null) {
            entity.setStato(this.getStato());
        }

        if (this.getUtenteId() != 0) {
            UtenteEntity utenteEntity = new UtenteEntity();
            utenteEntity.setId(this.getUtenteId());
            entity.setUtente(utenteEntity);
        }

        if (this.getDettagliOrdine() != null) {
            List<DettaglioOrdineEntity> dettaglioOrdineEntities = this.getDettagliOrdine().stream()
                .map(dettagliOrdineDto -> dettagliOrdineDto.toEntity(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            entity.setDettagliOrdine(dettaglioOrdineEntities);
            dettaglioOrdineEntities.forEach(d -> d.setOrdine(entity));
        }

        return entity;
    }
}

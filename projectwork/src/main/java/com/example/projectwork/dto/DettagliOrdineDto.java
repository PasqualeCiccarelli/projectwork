package com.example.projectwork.dto;

import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.ProdottoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DettagliOrdineDto {
    private Long id;
    private double prezzo;
    private int quantita;
    private Long ordineId;
    private ProdottoDto prodottoDto;
    
    public DettagliOrdineDto() {
		// TODO Auto-generated constructor stub
	}
    
    

    public DettagliOrdineDto(Long id, double prezzo, int quantita, Long ordineId, ProdottoDto prodottoDto) {
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.ordineId = ordineId;
		this.prodottoDto = prodottoDto;
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

	public ProdottoDto getProdottoDto() {
		return prodottoDto;
	}

	public void setProdottoDto(ProdottoDto prodottoDto) {
		this.prodottoDto = prodottoDto;
	}

	public static DettagliOrdineDto fromEntity(DettaglioOrdineEntity entity) {
        DettagliOrdineDto dto = new DettagliOrdineDto();
        dto.setId(entity.getId());
        dto.setPrezzo(entity.getPrezzo());
        dto.setQuantita(entity.getQuantita());
        dto.setOrdineId(entity.getOrdine().getId());

        ProdottoEntity prodotto = entity.getCarta() != null ? entity.getCarta()
                : entity.getBox() != null ? entity.getBox()
                : entity.getBustina() != null ? entity.getBustina()
                : entity.getAccessorio();

        if (prodotto != null) {
            dto.setProdottoDto(ProdottoDto.toDto(prodotto));
        }

        return dto;
    }

	public DettaglioOrdineEntity toEntity(DettaglioOrdineEntity existingEntity) {
	    DettaglioOrdineEntity entity = (existingEntity != null && this.getId() != null) ? existingEntity : new DettaglioOrdineEntity();
	    
	    entity.setPrezzo(this.getPrezzo());
	    entity.setQuantita(this.getQuantita());

	    if (this.getOrdineId() != null) {
	        OrdineEntity ordineEntity = new OrdineEntity();
	        ordineEntity.setId(this.getOrdineId());
	        entity.setOrdine(ordineEntity);
	    }

	    
	    if (this.getProdottoDto() != null) {
	        ProdottoDto prodottoDto = this.getProdottoDto();

	        switch (prodottoDto.getTipoCategoria()) {
	            case CARD:
	                CardEntity cardEntity = new CardEntity();
	                cardEntity.setId(prodottoDto.getId());
	                entity.setCarta(cardEntity);
	                break;
	            case BOX:
	                BoxEntity boxEntity = new BoxEntity();
	                boxEntity.setId(prodottoDto.getId());
	                entity.setBox(boxEntity);
	                break;
	            case BUSTINA:
	                BustinaEntity bustinaEntity = new BustinaEntity();
	                bustinaEntity.setId(prodottoDto.getId());
	                entity.setBustina(bustinaEntity);
	                break;
	            case ACCESSORIO:
	                AccessorioEntity accessorioEntity = new AccessorioEntity();
	                accessorioEntity.setId(prodottoDto.getId());
	                entity.setAccessorio(accessorioEntity);
	                break;
	            default:
	                throw new IllegalArgumentException("Tipo di prodotto non valido: " + prodottoDto.getTipoCategoria());
	        }
	    } else {
	        entity.setCarta(null);
	        entity.setBox(null);
	        entity.setBustina(null);
	        entity.setAccessorio(null);
	    }

	    return entity;
	}


}

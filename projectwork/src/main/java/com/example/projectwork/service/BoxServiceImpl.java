package com.example.projectwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BoxRequest;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.BoxRepository;
import com.example.projectwork.restCtrl.CardCtrl;
import com.example.projectwork.service.interf.BoxService;

@Service
public class BoxServiceImpl implements BoxService {
	
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
	private BoxRepository boxRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

	@Override
	public BoxEntity creaBox(BoxRequest boxRequest) {
		
		String emailAdmin = boxRequest.getEmailAdmin();

        if (emailAdmin == null || emailAdmin.isEmpty()) {
            throw new RuntimeException("Email dell'utente mancante.");
        }

        AdminEntity adminLoggato = adminRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Admin non trovato"));

        BoxEntity newBox = new BoxEntity();
        newBox.setNome(boxRequest.getNome());
        newBox.setDescrizione(boxRequest.getDescrizione());
        newBox.setCategoria(boxRequest.getCategoria());
        newBox.setTipo(boxRequest.getTipo());
        newBox.setBrand(boxRequest.getBrand());
        newBox.setPrezzo(boxRequest.getPrezzo());
        newBox.setRimanenza(boxRequest.getRimanenze());
        newBox.setDisponibilita(boxRequest.isDisponibilita());
        newBox.setImmagine(boxRequest.getImmagine());
        newBox.setDataInizio(boxRequest.getData_inizio());
        newBox.setPrezzoScontato(boxRequest.getPrezzo_scontato());
        newBox.setQuantitaCarte(boxRequest.getNumero_carte());
        newBox.setNumeroBustine(boxRequest.getNumero_bustine());
        newBox.setEdizione(boxRequest.getEdizione());
        newBox.setNomeSet(boxRequest.getNomeSet());
        newBox.setTipoCategoria(boxRequest.getTipoCategoria());
        newBox.setAdmin(adminLoggato);

        return boxRepository.save(newBox);
	}
	
	private List<BoxDto> getBoxByBrand(Brand brand) {
        try {
            List<BoxEntity> cards = boxRepository.findByBrand(brand);
            logger.info("Trovate {} carte per il brand {}", cards.size(), brand);
            return cards.stream()
                    .map(BoxDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Errore nel recupero delle carte per brand {}: {}", brand, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Errore nel recupero delle carte per brand: " + e.getMessage(), e);
        }
    }
	
	public List<BoxDto> getBoxByBrandPokemon() {
        return getBoxByBrand(Brand.POKEMON);
    }

    public List<BoxDto> getBoxByBrandMagic() {
        return getBoxByBrand(Brand.MAGIC);
    }

    public List<BoxDto> getBoxByBrandYugiho() {
        return getBoxByBrand(Brand.YUGIHO);
    }
    
    
    
    /**************************/
    
	public List<BoxDto> getCardByAccessori(Brand brand, Categoria categoria){
	    	
	    List<BoxEntity> lista= boxRepository.findByBrandAndCategoria(brand, categoria);
	    	
	    return lista.stream().map(BoxDto::fromEntity).collect(Collectors.toList());
	}
    
    
    
    
    
    
    
    
    
    
    

}

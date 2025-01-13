package com.example.projectwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AccessoriRequest;
import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.repository.AccessoriRepository;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.restCtrl.CardCtrl;
import com.example.projectwork.service.interf.AccessoriService;

@Service
public class AccessoriServiceImpl implements AccessoriService {

	@Autowired
	private AccessoriRepository accessoriRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

	@Override
	public AccessoriEntity creaAccessorio(AccessoriRequest accessoriRequest) {

		String emailAdmin = accessoriRequest.getEmailAdmin();

		if (emailAdmin == null || emailAdmin.isEmpty()) {
			throw new RuntimeException("Email dell'utente mancante.");
		}

		AdminEntity adminLoggato = adminRepository.findByEmail(emailAdmin)
				.orElseThrow(() -> new RuntimeException("Admin non trovato"));

		AccessoriEntity newAccessorio = new AccessoriEntity();
		newAccessorio.setNome(accessoriRequest.getNome());
		newAccessorio.setDescrizione(accessoriRequest.getDescrizione());
		newAccessorio.setCategoria(accessoriRequest.getCategoria());
		newAccessorio.setTipo(accessoriRequest.getTipo());
		newAccessorio.setBrand(accessoriRequest.getBrand());
		newAccessorio.setPrezzo(accessoriRequest.getPrezzo());
		newAccessorio.setRimanenza(accessoriRequest.getRimanenze());
		newAccessorio.setDisponibilita(accessoriRequest.isDisponibilita());
		newAccessorio.setImmagine(accessoriRequest.getImmagine());
		newAccessorio.setDataInizio(accessoriRequest.getData_inizio());
		newAccessorio.setPrezzoScontato(accessoriRequest.getPrezzo_scontato());
		newAccessorio.setDimensioni(accessoriRequest.getDimensioni());
		newAccessorio.setPeso(accessoriRequest.getPeso());
		newAccessorio.setAdmin(adminLoggato);

		return accessoriRepository.save(newAccessorio);
	}

	private List<AccessoriDto> getAccessoriByBrand(Brand brand) {
        try {
            List<AccessoriEntity> cards = accessoriRepository.findByBrand(brand);
            logger.info("Trovate {} carte per il brand {}", cards.size(), brand);
            return cards.stream()
                    .map(AccessoriDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Errore nel recupero delle carte per brand {}: {}", brand, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Errore nel recupero delle carte per brand: " + e.getMessage(), e);
        }
    }

	public List<AccessoriDto> getAccessoriByBrandPokemon() {
		return getAccessoriByBrand(Brand.POKEMON);
	}

	public List<AccessoriDto> getAccessoriByBrandMagic() {
		return getAccessoriByBrand(Brand.MAGIC);
	}

	public List<AccessoriDto> getAccessoriByBrandYugiho() {
		return getAccessoriByBrand(Brand.YUGIHO);
	}
	
	
	
	
	/******************************************/
    
    public List<AccessoriDto> getCardByAccessori(Brand brand, Categoria categoria){
    	
    	List<AccessoriEntity> lista= accessoriRepository.findByBrandAndAccessori(brand, categoria);
    	
    	return lista.stream().map(AccessoriDto::fromEntity).collect(Collectors.toList());
    }
    
    
    
    
	
	
	

}

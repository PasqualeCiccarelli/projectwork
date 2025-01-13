package com.example.projectwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.BustinaRequest;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.BustineRepository;
import com.example.projectwork.restCtrl.CardCtrl;
import com.example.projectwork.service.interf.BustinaService;

@Service
public class BustinaServiceImpl implements BustinaService {
	
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
	private BustineRepository bustineRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

	@Override
	public BustinaEntity creaBustina(BustinaRequest bustinaRequest) {
		
		String emailAdmin = bustinaRequest.getEmailAdmin();

        if (emailAdmin == null || emailAdmin.isEmpty()) {
            throw new RuntimeException("Email dell'utente mancante.");
        }

        AdminEntity adminLoggato = adminRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Admin non trovato"));

        BustinaEntity newBustina = new BustinaEntity();
        newBustina.setNome(bustinaRequest.getNome());
        newBustina.setDescrizione(bustinaRequest.getDescrizione());
        newBustina.setCategoria(bustinaRequest.getCategoria());
        newBustina.setTipo(bustinaRequest.getTipo());
        newBustina.setBrand(bustinaRequest.getBrand());
        newBustina.setPrezzo(bustinaRequest.getPrezzo());
        newBustina.setRimanenza(bustinaRequest.getRimanenze());
        newBustina.setDisponibilita(bustinaRequest.isDisponibilita());
        newBustina.setImmagine(bustinaRequest.getImmagine());
        newBustina.setDataInizio(bustinaRequest.getData_inizio());
        newBustina.setPrezzoScontato(bustinaRequest.getPrezzo_scontato());
        newBustina.setNumero_carte(bustinaRequest.getNumero_carte());
        newBustina.setAdmin(adminLoggato);


        return bustineRepository.save(newBustina);
	}
	
	private List<BustinaDto> getBustineByBrand(Brand brand) {
        try {
            List<BustinaEntity> cards = bustineRepository.findByBrand(brand);
            logger.info("Trovate {} carte per il brand {}", cards.size(), brand);
            return cards.stream()
                    .map(BustinaDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Errore nel recupero delle carte per brand {}: {}", brand, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Errore nel recupero delle carte per brand: " + e.getMessage(), e);
        }
    }
	
	public List<BustinaDto> getBustineByBrandPokemon() {
        return getBustineByBrand(Brand.POKEMON);
    }

    public List<BustinaDto> getBustineByBrandMagic() {
        return getBustineByBrand(Brand.MAGIC);
    }

    public List<BustinaDto> getBustineByBrandYugiho() {
        return getBustineByBrand(Brand.YUGIHO);
    }

}

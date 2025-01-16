package com.example.projectwork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.CardRepository;
import com.example.projectwork.restCtrl.CardCtrl;
import com.example.projectwork.service.interf.CardService;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

    @Override
    public CardEntity creaCard(CardRequest cardRequest) {

        String emailAdmin = cardRequest.getEmailAdmin();

        if (emailAdmin == null || emailAdmin.isEmpty()) {
            throw new RuntimeException("Email dell'utente mancante.");
        }

        AdminEntity adminLoggato = adminRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Admin non trovato"));

        CardEntity newCard = new CardEntity();
        newCard.setNome(cardRequest.getNome());
        newCard.setDescrizione(cardRequest.getDescrizione());
        newCard.setCategoria(cardRequest.getCategoria());
        newCard.setTipo(cardRequest.getTipo());
        newCard.setBrand(cardRequest.getBrand());
        newCard.setPrezzo(cardRequest.getPrezzo());
        newCard.setRimanenza(cardRequest.getRimanenze());
        newCard.setDisponibilita(cardRequest.isDisponibilita());
        newCard.setImmagine(cardRequest.getImmagine());
        newCard.setDataInizio(cardRequest.getData_inizio());
        newCard.setPrezzoScontato(cardRequest.getPrezzo_scontato());
        newCard.setEdizione(cardRequest.getEdizione());
        newCard.setRarita(cardRequest.getRarita());
        newCard.setAdmin(adminLoggato);

        return cardRepository.save(newCard);
    }
    
    public List<CardDto> getCardsByBrand(Brand brand) {
        try {
            List<CardEntity> cards = cardRepository.findByBrand(brand);
            logger.info("Trovate {} carte per il brand {}", cards.size(), brand);
            return cards.stream()
                    .map(CardDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Errore nel recupero delle carte per brand {}: {}", brand, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Errore nel recupero delle carte per brand: " + e.getMessage(), e);
        }
    }
    
    public List<CardDto> getCardsByBrandPokemon() {
        return getCardsByBrand(Brand.POKEMON);
    }
    
    public List<CardDto> getCardsByBrandMagic() {
        return getCardsByBrand(Brand.MAGIC);
    }
    
    public List<CardDto> getCardsByBrandYugiho() {
        return getCardsByBrand(Brand.YUGIHO);
    }

	

    
    
    
    /******************************************/
    
    public List<CardDto> getCardByCategoria(Brand brand, Categoria categoria){
    	
    	List<CardEntity> lista= cardRepository.findByBrandAndCategoria(brand, categoria);
    	
    	return lista.stream().map(CardDto::fromEntity).collect(Collectors.toList());
    }
    
    
    
    
    
    
    
    
}

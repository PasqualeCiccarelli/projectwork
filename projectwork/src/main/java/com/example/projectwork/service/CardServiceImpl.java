package com.example.projectwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.CardRepository;
import com.example.projectwork.service.interf.CardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private HttpServletRequest request;  // Per ottenere la richiesta HTTP e accedere ai cookie

    @Override
    public CardEntity creaCard(CardRequest cardRequest) {
        // Recupera l'email direttamente dalla richiesta
        String emailAdmin = cardRequest.getEmailAdmin();

        if (emailAdmin == null || emailAdmin.isEmpty()) {
            throw new RuntimeException("Email dell'utente mancante.");
        }

        // Recupera l'admin loggato dal database tramite l'email
        AdminEntity adminLoggato = adminRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Admin non trovato"));

        // Crea la nuova card
        CardEntity newCard = new CardEntity();
        newCard.setNome(cardRequest.getNome());
        newCard.setDescrizione(cardRequest.getDescrizione());
        newCard.setCategoria(cardRequest.getCategoria());
        newCard.setTipo(cardRequest.getTipo());
        newCard.setBrand(cardRequest.getBrand());
        newCard.setPrezzo(cardRequest.getPrezzo());
        newCard.setRimanenze(cardRequest.getRimanenze());
        newCard.setDisponibilita(cardRequest.isDisponibilita());
        newCard.setImmagine(cardRequest.getImmagine());
        newCard.setData_inizio(cardRequest.getData_inizio());
        newCard.setPrezzo_scontato(cardRequest.getPrezzo_scontato());
        newCard.setAdmin(adminLoggato);

        // Salva la card nel repository
        return cardRepository.save(newCard);
    }

    
}

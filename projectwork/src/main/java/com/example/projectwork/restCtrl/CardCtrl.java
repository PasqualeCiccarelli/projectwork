package com.example.projectwork.restCtrl;



import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Ruolo;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.service.interf.CardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/card")
public class CardCtrl {

  @Autowired
  private CardService cardService;

  private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

  @PostMapping("/crea")
  public ResponseEntity<?> creaCard(@RequestBody CardRequest cardRequest) {
    try {
      logger.info("Ricevuta richiesta di creazione carta: {}", cardRequest);

      if (cardRequest.getEmailAdmin() == null || cardRequest.getEmailAdmin().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email dell'utente mancante.");
      }

      CardEntity newCard = cardService.creaCard(cardRequest);

      return ResponseEntity.ok()
                           .body(Map.of("message", "Carta aggiunta con successo", "id", newCard.getId()));
    } catch (Exception e) {
      logger.error("Errore durante la creazione della carta", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno: " + e.getMessage());
    }
  }
}

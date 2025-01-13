package com.example.projectwork.restCtrl;




import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.service.interf.CardService;



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
  
  @GetMapping("/brand/pokemon")
  public List<CardDto> getCardsByBrandPokemon() {
      try {
          logger.info("Ricevuta richiesta per carte Pokemon");
          List<CardDto> cards = cardService.getCardsByBrandPokemon();
          logger.info("Restituite {} carte Pokemon", cards.size());
          return cards;
      } catch (Exception e) {
          logger.error("Errore nel controller durante il recupero delle carte Pokemon: {}", e.getMessage(), e);
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
              "Errore nel recupero delle carte Pokemon: " + e.getMessage(), e);
      }
  }

 
  @GetMapping("/brand/magic")
  public List<CardDto> getCardsByBrandMagic() {
      return cardService.getCardsByBrandMagic();
  }

 
  @GetMapping("/brand/yugiho")
  public List<CardDto> getCardsByBrandYugiho() {
      return cardService.getCardsByBrandYugiho();
  }
}

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

import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BoxRequest;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.service.interf.BoxService;


@RestController
@RequestMapping("/api/box")
public class BoxCtrl {
	
	 @Autowired
	  private BoxService boxService;

	  private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);

	  @PostMapping("/crea")
	  public ResponseEntity<?> creaBox(@RequestBody BoxRequest boxRequest) {
	    try {
	      logger.info("Ricevuta richiesta di creazione box: {}", boxRequest);

	      if (boxRequest.getEmailAdmin() == null || boxRequest.getEmailAdmin().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email dell'utente mancante.");
	      }

	      BoxEntity newBox = boxService.creaBox(boxRequest);

	      return ResponseEntity.ok()
	                           .body(Map.of("message", "Box aggiunto con successo", "id", newBox.getId()));
	    } catch (Exception e) {
	      logger.error("Errore durante la creazione del box", e);
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno: " + e.getMessage());
	    }
	  }
	  
	  @GetMapping("/brand/pokemon")
	  public List<BoxDto> getBoxPokemon() {
	      try {
	          logger.info("Ricevuta richiesta per box Pokemon");
	          List<BoxDto> box = boxService.getBoxByBrandPokemon();
	          logger.info("Restituite {} box Pokemon", box.size());
	          return box;
	      } catch (Exception e) {
	          logger.error("Errore nel controller durante il recupero del box Pokemon: {}", e.getMessage(), e);
	          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
	              "Errore nel recupero del box Pokemon: " + e.getMessage(), e);
	      }
	  }

	    @GetMapping("/brand/magic")
	    public List<BoxDto> getBoxMagic() {
	        return boxService.getBoxByBrandMagic();
	    }

	    @GetMapping("/brand/yugiho")
	    public List<BoxDto> getBoxYugiho() {
	        return boxService.getBoxByBrandYugiho();
	    }

}

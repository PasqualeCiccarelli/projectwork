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

import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.BustinaRequest;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.service.interf.BustinaService;

@RestController
@RequestMapping("/api/bustina")
public class BustinaCtrl {
	
	private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);
	
	@Autowired
	private BustinaService bustinaService;
	
	@PostMapping("/crea")
	  public ResponseEntity<?> creaBustina(@RequestBody BustinaRequest bustinaRequest) {
	    try {
	      logger.info("Ricevuta richiesta di creazione b: {}", bustinaRequest);

	      if (bustinaRequest.getEmailAdmin() == null || bustinaRequest.getEmailAdmin().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email dell'utente mancante.");
	      }

	      BustinaEntity newBustina = bustinaService.creaBustina(bustinaRequest);

	      return ResponseEntity.ok()
	                           .body(Map.of("message", "Bustina aggiunta con successo", "id", newBustina.getId()));
	    } catch (Exception e) {
	      logger.error("Errore durante la creazione della bustina", e);
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno: " + e.getMessage());
	    }
	  }
	
	 @GetMapping("/brand/pokemon")
	  public List<BustinaDto> getBustinePokemon() {
	      try {
	          logger.info("Ricevuta richiesta per bustna Pokemon");
	          List<BustinaDto> bustine = bustinaService.getBustineByBrandPokemon();
	          logger.info("Restituite {} bustine Pokemon", bustine.size());
	          return bustine;
	      } catch (Exception e) {
	          logger.error("Errore nel controller durante il recupero delle bustine Pokemon: {}", e.getMessage(), e);
	          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
	              "Errore nel recupero delle bustine Pokemon: " + e.getMessage(), e);
	      }
	  }

    @GetMapping("/brand/magic")
    public List<BustinaDto> getBustineMagic() {
        return bustinaService.getBustineByBrandMagic();
    }

    @GetMapping("/brand/yugiho")
    public List<BustinaDto> getBustineYugiho() {
        return bustinaService.getBustineByBrandYugiho();
    }

}

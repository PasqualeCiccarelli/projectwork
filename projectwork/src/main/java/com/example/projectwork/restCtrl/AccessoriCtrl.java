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

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AccessoriRequest;
import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.service.interf.AccessoriService;

@RestController
@RequestMapping("/api/accessori")
public class AccessoriCtrl {
	
	@Autowired
	private AccessoriService accessoriService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CardCtrl.class);
	
	@PostMapping("/crea")
	  public ResponseEntity<?> creaAccessorio(@RequestBody AccessoriRequest accessoriRequest) {
	    try {
	      logger.info("Ricevuta richiesta di creazione accessorio: {}", accessoriRequest);

	      if (accessoriRequest.getEmailAdmin() == null || accessoriRequest.getEmailAdmin().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email dell'utente mancante.");
	      }

	      AccessoriEntity newAccessorio = accessoriService.creaAccessorio(accessoriRequest);

	      return ResponseEntity.ok()
	                           .body(Map.of("message", "Accessorio aggiunto con successo", "id", newAccessorio.getId()));
	    } catch (Exception e) {
	      logger.error("Errore durante la creazione dell'accessorio", e);
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno: " + e.getMessage());
	    }
	  }
	
	@GetMapping("/brand/pokemon")
	  public List<AccessoriDto> getAccessoriPokemon() {
	      try {
	          logger.info("Ricevuta richiesta per accessori Pokemon");
	          List<AccessoriDto> accessori = accessoriService.getAccessoriByBrandPokemon();
	          logger.info("Restituite {} accessori Pokemon", accessori.size());
	          return accessori;
	      } catch (Exception e) {
	          logger.error("Errore nel controller durante il recupero degli accessori Pokemon: {}", e.getMessage(), e);
	          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
	              "Errore nel recupero degli accessori Pokemon: " + e.getMessage(), e);
	      }
	  }

    @GetMapping("/brand/magic")
    public List<AccessoriDto> getAccessoriMagic() {
        return accessoriService.getAccessoriByBrandMagic();
    }

    @GetMapping("/brand/yugiho")
    public List<AccessoriDto> getAccessoriYugiho() {
        return accessoriService.getAccessoriByBrandYugiho();
    }

}

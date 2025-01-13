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
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.BustinaRequest;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
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
    
    
    
    /********************/
    
    @GetMapping("/brand/pokemon-prevendita")
    public ResponseEntity<List<BustinaDto>> getPokemonPrevenditaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.POKEMON, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-novita")
    public ResponseEntity<List<BustinaDto>> getPokemonNovitaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.POKEMON, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-default")
    public ResponseEntity<List<BustinaDto>> getPokemonDefaultCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.POKEMON, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-speciale")
    public ResponseEntity<List<BustinaDto>> getPokemonSpecialeCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.POKEMON, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    /********************/
    
    @GetMapping("/brand/magic-prevendita")
    public ResponseEntity<List<BustinaDto>> getMagicPrevenditaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.MAGIC, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-novita")
    public ResponseEntity<List<BustinaDto>> getMagicNovitaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.MAGIC, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-default")
    public ResponseEntity<List<BustinaDto>> getMagicDefaultCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.MAGIC, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-speciale")
    public ResponseEntity<List<BustinaDto>> getMagicSpecialeCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.MAGIC, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    
    /********************/
    
    @GetMapping("/brand/yugiho-prevendita")
    public ResponseEntity<List<BustinaDto>> getYugihoPrevenditaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.YUGIHO, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-novita")
    public ResponseEntity<List<BustinaDto>> getYugihoNovitaCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.YUGIHO, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-default")
    public ResponseEntity<List<BustinaDto>> getYugihoDefaultCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.YUGIHO, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-speciale")
    public ResponseEntity<List<BustinaDto>> getYugihoSpecialeCard(){
  	  
  	  List<BustinaDto> lista= bustinaService.getCardByAccessori(Brand.YUGIHO, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

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
import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Tipo;
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

	      AccessorioEntity newAccessorio = accessoriService.creaAccessorio(accessoriRequest);

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
    
    
    
    /********************/
    
    @GetMapping("/brand/pokemon-prevendita")
    public ResponseEntity<List<AccessoriDto>> getPokemonPrevenditaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.POKEMON, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-novita")
    public ResponseEntity<List<AccessoriDto>> getPokemonNovitaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.POKEMON, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-default")
    public ResponseEntity<List<AccessoriDto>> getPokemonDefaultCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.POKEMON, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/pokemon-speciale")
    public ResponseEntity<List<AccessoriDto>> getPokemonSpecialeCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.POKEMON, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    /********************/
    
    @GetMapping("/brand/magic-prevendita")
    public ResponseEntity<List<AccessoriDto>> getMagicPrevenditaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.MAGIC, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-novita")
    public ResponseEntity<List<AccessoriDto>> getMagicNovitaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.MAGIC, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-default")
    public ResponseEntity<List<AccessoriDto>> getMagicDefaultCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.MAGIC, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/magic-speciale")
    public ResponseEntity<List<AccessoriDto>> getMagicSpecialeCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.MAGIC, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    
    /********************/
    
    @GetMapping("/brand/yugiho-prevendita")
    public ResponseEntity<List<AccessoriDto>> getYugihoPrevenditaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.YUGIHO, Categoria.PREVENDITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-novita")
    public ResponseEntity<List<AccessoriDto>> getYugihoNovitaCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.YUGIHO, Categoria.NOVITA);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-default")
    public ResponseEntity<List<AccessoriDto>> getYugihoDefaultCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.YUGIHO, Categoria.DEFAULT);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/brand/yugiho-speciale")
    public ResponseEntity<List<AccessoriDto>> getYugihoSpecialeCard(){
  	  
  	  List<AccessoriDto> lista= accessoriService.getCardByAccessori(Brand.YUGIHO, Categoria.SPECIALE);
  	  
  	  return ResponseEntity.ok(lista);
    }
    
    
    
    /*******************Accessori - Tipo- Pokemon*****************/
    
    @GetMapping("/brand/pokemon/action-figure")
    public ResponseEntity<List<AccessoriDto>> getPokemonTipoActionFigure(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.POKEMON, Tipo.ACTION_FIGURE);
    	  
    	return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/brand/pokemon/gadget")
    public ResponseEntity<List<AccessoriDto>> getPokemonTipoGadget(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.POKEMON, Tipo.GADGET);
    	  
    	return ResponseEntity.ok(lista);
    }
    
    
    
    /*******************Accessori - Tipo- Magic*****************/
    
    @GetMapping("/brand/magic/action-figure")
    public ResponseEntity<List<AccessoriDto>> getMagicTipoActionFigure(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.MAGIC, Tipo.ACTION_FIGURE);
    	  
    	return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/brand/magic/gadget")
    public ResponseEntity<List<AccessoriDto>> getMagicTipoGadget(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.MAGIC, Tipo.GADGET);
    	  
    	return ResponseEntity.ok(lista);
    }
    
   
    /*******************Accessori - Tipo- Yugiho*****************/
    
    @GetMapping("/brand/yugiho/action-figure")
    public ResponseEntity<List<AccessoriDto>> getYugihoTipoActionFigure(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.YUGIHO, Tipo.ACTION_FIGURE);
    	  
    	return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/brand/yugiho/gadget")
    public ResponseEntity<List<AccessoriDto>> getYugihoTipoGadget(){
    	  
    	List<AccessoriDto> lista= accessoriService.getCardByAccessoriByTipo(Brand.YUGIHO, Tipo.GADGET);
    	  
    	return ResponseEntity.ok(lista);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

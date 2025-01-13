package com.example.projectwork.restCtrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.service.interf.OrdineService;

@RestController
@RequestMapping("/api/ordini")
public class OrdineCtrl {

	@Autowired
	private OrdineService ordineService;
	
	
	@PostMapping("/crea-ordine-e-aggiungi-prodotto")
	public ResponseEntity<DettagliOrdineDto> creaOrdineEAggiungiProdotto(
	        @RequestParam String emailUtente, 
	        @RequestParam String tipoProdotto, 
	        @RequestParam Long idProdotto, 
	        @RequestParam Integer quantita) {
	    DettaglioOrdineRequest request = new DettaglioOrdineRequest();
	    request.setTipoProdotto(tipoProdotto);
	    request.setProductId(idProdotto);
	    request.setQuantita(quantita);

	    Long userId = ordineService.getUserIdByEmail(emailUtente);
	    return ResponseEntity.ok(ordineService.aggiungiProdotto(userId, request));
	}

	@PostMapping("/aggiungi-prodotto")
	public ResponseEntity<DettagliOrdineDto> aggiungiProdotto(@RequestParam Long userId,
			@RequestBody DettaglioOrdineRequest request) {
		return ResponseEntity.ok(ordineService.aggiungiProdotto(userId, request));
	}

	@DeleteMapping("/dettaglio/{dettaglioId}")
	public ResponseEntity<Void> rimuoviDettaglio(@PathVariable Long dettaglioId) {
		ordineService.rimuoviDettaglio(dettaglioId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{ordineId}/conferma")
	public ResponseEntity<Void> confermaOrdine(@PathVariable Long ordineId) {
		ordineService.confermaOrdine(ordineId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{ordineId}/annulla")
	public ResponseEntity<Void> annullaOrdine(@PathVariable Long ordineId) {
		ordineService.annullaOrdine(ordineId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ordineId}/totale")
    public ResponseEntity<Double> getTotaleOrdine(@PathVariable Long ordineId) {
        return ResponseEntity.ok(ordineService.calcolaTotaleOrdine(ordineId));
    }

}

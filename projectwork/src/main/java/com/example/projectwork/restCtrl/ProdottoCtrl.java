package com.example.projectwork.restCtrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.service.interf.ProdottoService;

@RestController
@RequestMapping("/api/prodotto")
public class ProdottoCtrl {
	
	@Autowired
	private ProdottoService prodottoService;
	
	@GetMapping("/dettagli-prodotto")
	public ResponseEntity<ProdottoDto> getProdottoById(@RequestParam("id") Long id) {
        ProdottoDto prodottoDto = prodottoService.getProdottoById(id);
        return ResponseEntity.ok(prodottoDto);
    }
	
	
	@PutMapping("/modifica-categoria")
	public ResponseEntity<ProdottoDto> modCategoriaProd(@RequestParam("id") Long id, @RequestParam("categoria") Categoria categoria){
		ProdottoDto prodotto= prodottoService.modificaCategoriaProdotto(id, categoria);
		return ResponseEntity.ok(prodotto);
	}
	
	
	@DeleteMapping("/elimina-prodotto")
	public ResponseEntity<Integer> deleteProdottoUtente(@RequestParam("id") Long id) {
		try {
			prodottoService.eliminaProdottoUtente(id);
			
			return ResponseEntity.ok(1);
		}
		catch(Exception e) {
			return ResponseEntity.internalServerError().body(0);
		}
	}

}

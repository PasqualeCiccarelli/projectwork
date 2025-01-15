package com.example.projectwork.restCtrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.ProdottoDto;
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

}

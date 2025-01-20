package com.example.projectwork.restCtrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.Operation;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Rarita;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.entity.entityenum.Tipo;
import com.example.projectwork.entity.entityenum.TipoCategoria;
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
	
	@GetMapping
	public ResponseEntity<Page<ProdottoDto>> getProdotti(
	        @RequestParam(required = false) TipoCategoria tipoCategoria,
	        @RequestParam(required = false) Rarita rarita,
	        @RequestParam(required = false) Categoria categoria,
	        @RequestParam(required = false) Stato stato,
	        @RequestParam(required = false) Brand brand,
	        @RequestParam(required = false) Double prezzoMinimo,  
	        @RequestParam(required = false) Double prezzoMassimo,  
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "12") int size) {
	    Page<ProdottoDto> prodotti = prodottoService.getProdottiFiltrati(
	            tipoCategoria, rarita, categoria, stato, brand, prezzoMinimo, prezzoMassimo, page, size);
	    return ResponseEntity.ok(prodotti);
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
	
	@GetMapping("/search")
    public ResponseEntity<List<ProdottoDto>> searchProdotti(@RequestParam("query") String query) {
        List<ProdottoDto> risultati = prodottoService.searchProdotti(query);
        return ResponseEntity.ok(risultati);
    }
	
	@GetMapping("/brand-categoria")
    public List<ProdottoDto> getProdottiByBrandAndCategoria(@RequestParam Brand brand, @RequestParam Categoria categoria) {
        return prodottoService.getProdottiByBrandAndCategoria(brand, categoria);
    }
	
	@GetMapping("/brand-tipoCategoria-tipi")
	public List<ProdottoDto> getProdottiByBrandTipoCategoriaAndTipi(@RequestParam Brand brand, 
	                                                                @RequestParam TipoCategoria tipoCategoria, 
	                                                                @RequestParam List<Tipo> tipi) {
		return prodottoService.getProdottiByBrandTipoCategoriaAndTipi(brand, tipoCategoria, tipi);
	}
	
	@GetMapping("/top-venduti")
	@Operation(
	    summary = "Recupera i 20 prodotti più venduti filtrati per brand",
	    description = "Restituisce una lista dei 20 prodotti più venduti per un brand specifico"
	)
	public ResponseEntity<List<ProdottoDto>> getTopSellingProducts(@RequestParam Brand brand) {
	    List<ProdottoDto> topProducts = prodottoService.findTop20SellingProductsByBrand(brand);
	    return ResponseEntity.ok(topProducts);
	}

}

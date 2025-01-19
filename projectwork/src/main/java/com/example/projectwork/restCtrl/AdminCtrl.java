package com.example.projectwork.restCtrl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.AdminDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BustinaDto;
import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.service.interf.AdminService;
import com.example.projectwork.service.interf.ProdottoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin")
public class AdminCtrl {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	
	
	
	@GetMapping("/{email}/cards")
	public ResponseEntity<List<CardDto>> getCardsByAdmin(@PathVariable String email){
		
		List<CardDto> lista= adminService.getCardByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{email}")
    public ResponseEntity<AdminDto> getAdminByEmail(@PathVariable String email) {
        try {
            AdminDto adminDto = adminService.getAdminByEmail(email);
            return ResponseEntity.ok(adminDto); // Restituisce l'AdminDto
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(null); // Se non trovato, restituisce 404
        }
    }
	
	@GetMapping("/{email}/box")
	public ResponseEntity<List<BoxDto>> getBoxByAdmin(@PathVariable String email){
		
		List<BoxDto> lista= adminService.getBoxByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{email}/bustine")
	public ResponseEntity<List<BustinaDto>> getBustineByAdmin(@PathVariable String email){
		
		List<BustinaDto> lista= adminService.getBustineByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{email}/accessori")
	public ResponseEntity<List<AccessoriDto>> getAccessoriByAdmin(@PathVariable String email){
		
		List<AccessoriDto> lista= adminService.getAccessoriByAdmin(email);
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/prodotti")
    public ResponseEntity<Page<ProdottoEntity>> getProdottiAdmin(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        
        return ResponseEntity.ok(adminService.getProdottiByAdmin(email, page, size));
    }
	
	@PutMapping("/prodotti/{id}/categoria")
	public ResponseEntity<ProdottoDto> modificaCategoriaProdotto(@PathVariable Long id, @RequestParam Categoria categoria) {
	    try {
	     
	        ProdottoDto prodotto = prodottoService.modificaCategoriaProdotto(id, categoria);

	        return ResponseEntity.ok(prodotto);
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().build();
	    }
	}

}


























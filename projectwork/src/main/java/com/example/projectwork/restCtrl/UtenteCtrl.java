package com.example.projectwork.restCtrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.UtenteImmagineRequest;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.service.interf.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteCtrl {

    @Autowired
    private UtenteService utenteService;
    
    @PostMapping("/aggiorna-immagine")
    public ResponseEntity<String> aggiornaImmagineProfilo(@RequestBody UtenteImmagineRequest immagineRequest) {
        try {
            String response = utenteService.aggiornaImmagineProfilo(immagineRequest.getEmail(), immagineRequest.getImmagine());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/{email}")
    public UtenteEntity getUtente(@PathVariable String email) {
        return utenteService.getUtenteByEmail(email);
    }

    
}

package com.example.projectwork.restCtrl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectwork.dto.AggiungiProdottoRequest;
import com.example.projectwork.dto.CompletaOrdineRequest;
import com.example.projectwork.dto.EliminaOrdineRequest;
import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.dto.RimuoviProdottoRequest;
import com.example.projectwork.eccezioni.ErrorResponse;
import com.example.projectwork.eccezioni.ResourceNotFoundException;
import com.example.projectwork.eccezioni.UnauthorizedException;
import com.example.projectwork.service.interf.OrdineService;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ordini")
@RequiredArgsConstructor
public class OrdineCtrl {

    @Autowired
    private OrdineService ordineService;
    
    @PostMapping("/aggiungi-prodotto")
    public ResponseEntity<OrdineDto> aggiungiProdotto(@RequestBody AggiungiProdottoRequest request) {
        System.out.println("Richiesta ricevuta: " + request);
        return ResponseEntity.ok(ordineService.aggiungiProdottoAlCarrello(
            request.getEmail(), 
            request.getProdotto(), 
            request.getQuantita()
        ));
    }
    
    @GetMapping("/{email}/carrello")
    public ResponseEntity<OrdineDto> getCarrello(@PathVariable String email) {
        return ResponseEntity.ok(ordineService.getOrdineInCorso(email));
    }
    
    @PostMapping("/rimuovi-prodotto")
    public ResponseEntity<?> rimuoviProdotto(@RequestBody RimuoviProdottoRequest request) {
        try {
            OrdineDto ordineAggiornato = ordineService.rimuoviProdottoDaCarrello(
                request.getEmail(),
                request.getDettaglioId(),
                request.getQuantita()
            );
            return ResponseEntity.ok(ordineAggiornato);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Errore interno del server"));
        }
    }

    
    @DeleteMapping("/elimina")
    public ResponseEntity<Void> eliminaOrdine(@RequestBody EliminaOrdineRequest request) {
        ordineService.eliminaOrdineInCorso(request.getEmail());
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/completa")
    public ResponseEntity<OrdineDto> completaOrdine(@RequestBody CompletaOrdineRequest request) {
        return ResponseEntity.ok(ordineService.completaOrdine(request.getEmail(), request.getIndirizzo()));
    }
    
    @GetMapping("/in-arrivo/{email}")
    public ResponseEntity<List<OrdineDto>> getOrdiniInArrivo(@PathVariable String email) {
        return ResponseEntity.ok(ordineService.getOrdiniInArrivo(email));
    }
    
    @GetMapping("/consegnati/{email}")
    public ResponseEntity<List<OrdineDto>> getOrdiniConsegnati(@PathVariable String email) {
        return ResponseEntity.ok(ordineService.getOrdiniConsegnati(email));
    }
    
    @DeleteMapping("/elimina-ordine")
    public ResponseEntity<Void> eliminaOrdine(@RequestParam String email, @RequestParam Long ordineId) {
        ordineService.eliminaOrdine(email, ordineId);
        return ResponseEntity.ok().build();
    }
    

}

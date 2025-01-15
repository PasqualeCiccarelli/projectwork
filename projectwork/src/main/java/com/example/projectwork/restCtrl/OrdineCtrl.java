package com.example.projectwork.restCtrl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
import com.example.projectwork.dto.CreaOrdineRequest;
import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.dto.EliminaOrdineRequest;
import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.dto.RimuoviProdottoRequest;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.service.interf.OrdineService;

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
    public ResponseEntity<OrdineDto> rimuoviProdotto(@RequestBody RimuoviProdottoRequest request) {
        return ResponseEntity.ok(ordineService.rimuoviProdottoDaCarrello(
            request.getEmail(),
            request.getDettaglioId(),
            request.getQuantita()
        ));
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

}

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

import com.example.projectwork.dto.CreaOrdineRequest;
import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.service.interf.OrdineService;

@RestController
@RequestMapping("/api/ordini")
public class OrdineCtrl {

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/crea-ordine-e-aggiungi-prodotto")
    public ResponseEntity<DettagliOrdineDto> creaOrdineEAggiungiProdotto(@RequestBody CreaOrdineRequest creaOrdineRequest) {
        System.out.println("Ricevuto CreaOrdineRequest: " + creaOrdineRequest);
        Long userId = ordineService.getUserIdByEmail(creaOrdineRequest.getEmailUtente());
        DettaglioOrdineRequest ordineRequest = new DettaglioOrdineRequest();
        ordineRequest.setTipoProdotto(creaOrdineRequest.getTipoProdotto());
        ordineRequest.setProductId(creaOrdineRequest.getIdProdotto());
        ordineRequest.setQuantita(creaOrdineRequest.getQuantita());

        return ResponseEntity.ok(ordineService.aggiungiProdotto(userId, ordineRequest));
    }

    @PostMapping("/aggiungi-prodotto")
    public ResponseEntity<DettagliOrdineDto> aggiungiProdotto(@RequestParam Long userId,
            @RequestBody DettaglioOrdineRequest request) {
        return ResponseEntity.ok(ordineService.aggiungiProdotto(userId, request));
    }

    @DeleteMapping("rimuovi/dettaglio/{dettaglioId}")
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

    @GetMapping("/in-corso/{email}")
    public ResponseEntity<OrdineDto> getOrdineInCorso(@PathVariable String email) {
        try {
            email = URLDecoder.decode(email, StandardCharsets.UTF_8.name());
            Long userId = ordineService.getUserIdByEmail(email);
            OrdineEntity ordine = ordineService.getOrdineInCorso(userId);
            return ResponseEntity.ok(OrdineDto.fromEntity(ordine));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

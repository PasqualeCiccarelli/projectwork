package com.example.projectwork.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.eccezioni.UnauthorizedException;
import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.repository.AccessoriRepository;
import com.example.projectwork.repository.BoxRepository;
import com.example.projectwork.repository.BustineRepository;
import com.example.projectwork.repository.CardRepository;
import com.example.projectwork.repository.DettagliOrdineRepository;
import com.example.projectwork.repository.OrdineRepository;
import com.example.projectwork.repository.ProdottoRepository;
import com.example.projectwork.repository.UtenteRepository;
import com.example.projectwork.service.interf.OrdineService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdineServiceImpl implements OrdineService{

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private DettagliOrdineRepository dettagliOrdineRepository;

    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private ProdottoRepository prodottoRepository;
    
    @Transactional
    public OrdineDto aggiungiProdottoAlCarrello(String email, ProdottoDto prodottoDto, int quantita) {
        UtenteEntity utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        ProdottoEntity prodotto = prodottoRepository.findById(prodottoDto.getId())
            .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        if (prodotto.getRimanenza() < quantita) {
            throw new RuntimeException("Quantità richiesta non disponibile");
        }

        OrdineEntity ordine = ordineRepository.findByUtenteAndStato(utente, Stato.IN_CORSO)
            .orElseGet(() -> {
                OrdineEntity nuovoOrdine = new OrdineEntity();
                nuovoOrdine.setUtente(utente);
                nuovoOrdine.setStato(Stato.IN_CORSO);
                nuovoOrdine.setData(LocalDate.now());
                nuovoOrdine.setDettagliOrdine(new ArrayList<>());
                return ordineRepository.save(nuovoOrdine);
            });

        prodotto.setRimanenza(prodotto.getRimanenza() - quantita);
        prodottoRepository.save(prodotto);

        DettaglioOrdineEntity dettaglio = new DettaglioOrdineEntity();
        dettaglio.setOrdine(ordine);
        dettaglio.setPrezzo(prodotto.getPrezzo());
        dettaglio.setQuantita(quantita);

        if (prodotto instanceof CardEntity) {
            dettaglio.setCarta((CardEntity) prodotto);
        } else if (prodotto instanceof BoxEntity) {
            dettaglio.setBox((BoxEntity) prodotto);
        } else if (prodotto instanceof BustinaEntity) {
            dettaglio.setBustina((BustinaEntity) prodotto);
        } else if (prodotto instanceof AccessorioEntity) {
            dettaglio.setAccessorio((AccessorioEntity) prodotto);
        }

        ordine.getDettagliOrdine().add(dettaglio);
        ordine = ordineRepository.save(ordine);

        return OrdineDto.fromEntity(ordine);
    }

    public OrdineDto getOrdineInCorso(String email) {
        UtenteEntity utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        OrdineEntity ordine = ordineRepository.findByUtenteAndStato(utente, Stato.IN_CORSO)
            .orElseThrow(() -> new RuntimeException("Nessun ordine in corso"));

        return OrdineDto.fromEntity(ordine);
    }

    @Transactional
    public void eliminaOrdineInCorso(String email) {
        UtenteEntity utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        OrdineEntity ordine = ordineRepository.findByUtenteAndStato(utente, Stato.IN_CORSO)
            .orElseThrow(() -> new RuntimeException("Nessun ordine in corso"));

        ordineRepository.delete(ordine);
    }

    @Transactional
    public OrdineDto completaOrdine(String email, String indirizzo) {
        UtenteEntity utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        OrdineEntity ordine = ordineRepository.findByUtenteAndStato(utente, Stato.IN_CORSO)
            .orElseThrow(() -> new RuntimeException("Nessun ordine in corso"));

        ordine.setIndirizzo(indirizzo);
        ordine.setStato(Stato.ORDINATO);
        ordine = ordineRepository.save(ordine);

        return OrdineDto.fromEntity(ordine);
    }
    
    public OrdineDto rimuoviProdottoDaCarrello(String email, Long dettaglioId, int quantita) {
    	
        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantità deve essere maggiore di zero");
        }

        UtenteEntity utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con email: " + email));

        DettaglioOrdineEntity dettaglio = dettagliOrdineRepository.findById(dettaglioId)
            .orElseThrow(() -> new EntityNotFoundException("Dettaglio ordine non trovato con id: " + dettaglioId));

        if (dettaglio.getOrdine().getUtente().getId() != utente.getId()) {
            throw new UnauthorizedException("Non sei autorizzato a modificare questo ordine");
        }

        if (quantita > dettaglio.getQuantita()) {
            throw new IllegalArgumentException("Quantità richiesta maggiore di quella presente nel carrello");
        }

        ProdottoEntity prodotto = getProdottoFromDettaglio(dettaglio);
        if (prodotto == null) {
            throw new RuntimeException("Nessun prodotto trovato nel dettaglio ordine");
        }

        prodotto.setRimanenza(prodotto.getRimanenza() + quantita);
        prodottoRepository.save(prodotto);

        OrdineEntity ordine = dettaglio.getOrdine();
        if (dettaglio.getQuantita() == quantita) {
            dettagliOrdineRepository.delete(dettaglio);

            if (ordine.getDettagliOrdine().size() <= 1) {
                return new OrdineDto();
            }
        } else {
            dettaglio.setQuantita(dettaglio.getQuantita() - quantita);
            dettagliOrdineRepository.save(dettaglio);
        }

        return OrdineDto.fromEntity(ordine);
    }

    private ProdottoEntity getProdottoFromDettaglio(DettaglioOrdineEntity dettaglio) {
        if (dettaglio.getCarta() != null) return dettaglio.getCarta();
        if (dettaglio.getBox() != null) return dettaglio.getBox();
        if (dettaglio.getBustina() != null) return dettaglio.getBustina();
        if (dettaglio.getAccessorio() != null) return dettaglio.getAccessorio();
        return null;
    }

}

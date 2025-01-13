package com.example.projectwork.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.entity.AccessoriEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.DettaglioOrdineEntity;
import com.example.projectwork.entity.OrdineEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.repository.AccessoriRepository;
import com.example.projectwork.repository.BoxRepository;
import com.example.projectwork.repository.BustineRepository;
import com.example.projectwork.repository.CardRepository;
import com.example.projectwork.repository.DettagliOrdineRepository;
import com.example.projectwork.repository.OrdineRepository;
import com.example.projectwork.repository.UtenteRepository;
import com.example.projectwork.service.interf.OrdineService;

@Service
public class OrdineServiceImpl implements OrdineService{

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private DettagliOrdineRepository dettagliOrdineRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BustineRepository bustineRepository;

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private AccessoriRepository accessoriRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Long getUserIdByEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con l'email fornita"))
                .getId();
    }

    private void verificaDisponibilita(DettaglioOrdineRequest request) {
        ProdottoEntity prodotto = getProdottoEntity(request.getTipoProdotto(), request.getProductId());
        if (!prodotto.isDisponibilita()) {
            throw new RuntimeException("Il prodotto non è disponibile per l'acquisto");
        }
        if (prodotto.getRimanenza() < request.getQuantita()) {
            throw new RuntimeException("Rimanenze insufficienti. Disponibili: " + prodotto.getRimanenza()
                    + ", Richiesti: " + request.getQuantita());
        }
    }

    private ProdottoEntity getProdottoEntity(String tipoProdotto, Long productId) {
        switch (tipoProdotto) {
            case "CARTA":
                return cardRepository.findById(productId).orElseThrow(() -> new RuntimeException("Carta non trovata"));
            case "BOX":
                return boxRepository.findById(productId).orElseThrow(() -> new RuntimeException("Box non trovato"));
            case "BUSTINA":
                return bustineRepository.findById(productId).orElseThrow(() -> new RuntimeException("Bustina non trovata"));
            case "ACCESSORIO":
                return accessoriRepository.findById(productId).orElseThrow(() -> new RuntimeException("Accessorio non trovato"));
            default:
                throw new RuntimeException("Tipo prodotto non valido");
        }
    }

    private void setProdottoEPrezzo(DettaglioOrdineEntity dettaglio, DettaglioOrdineRequest request) {
        ProdottoEntity prodotto = getProdottoEntity(request.getTipoProdotto(), request.getProductId());
        dettaglio.setProdotto(prodotto);
        dettaglio.setPrezzo(prodotto.getPrezzoFinale());
    }

    @Scheduled(fixedDelay = 3600000)
    public void aggiornaStatoOrdini() {
        List<OrdineEntity> ordini = ordineRepository
                .findAllByStatoNotIn(Arrays.asList(Stato.CONSEGNATO, Stato.ANNULLATO));

        LocalDate now = LocalDate.now();

        for (OrdineEntity ordine : ordini) {
            switch (ordine.getStato_consegna()) {
                case ORDINATO:
                    if (ChronoUnit.DAYS.between(ordine.getData(), now) >= 3) {
                        ordine.setStato_consegna(Stato.SPEDITO);
                    }
                    break;
                case SPEDITO:
                    if (ChronoUnit.DAYS.between(ordine.getData(), now) >= 5) {
                        ordine.setStato_consegna(Stato.IN_CONSEGNA);
                    }
                    break;
                case IN_CONSEGNA:
                    if (ChronoUnit.HOURS.between(ordine.getData().atStartOfDay(), now.atStartOfDay()) >= 12) {
                        ordine.setStato_consegna(Stato.CONSEGNATO);
                    }
                    break;
            }
        }
        ordineRepository.saveAll(ordini);
    }

    public DettagliOrdineDto aggiungiProdotto(Long userId, DettaglioOrdineRequest request) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID non valido");
        }

        if (request == null) {
            throw new IllegalArgumentException("La richiesta non può essere nulla");
        }

        OrdineEntity ordine = ordineRepository.findByUtenteIdAndStato_consegna(userId, Stato.IN_CORSO).orElseGet(() -> {
            OrdineEntity nuovoOrdine = new OrdineEntity();
            nuovoOrdine.setUtente(utenteRepository.findById(userId).orElseThrow());
            nuovoOrdine.setStato_consegna(Stato.IN_CORSO);
            nuovoOrdine.setData(LocalDate.now());
            nuovoOrdine.setIndirizzo(request.getIndirizzo());
            return ordineRepository.save(nuovoOrdine);
        });

        verificaDisponibilita(request);

        DettaglioOrdineEntity dettaglio = new DettaglioOrdineEntity();
        dettaglio.setOrdine(ordine);
        dettaglio.setQuantita(request.getQuantita());
        setProdottoEPrezzo(dettaglio, request);

        return DettagliOrdineDto.fromEntity(dettagliOrdineRepository.save(dettaglio));
    }

    private boolean isCategoriaSpeciale(Categoria categoria) {
        return Categoria.SPECIALE.equals(categoria);
    }

    public void rimuoviDettaglio(Long dettaglioId) {
        DettaglioOrdineEntity dettaglio = dettagliOrdineRepository.findById(dettaglioId)
                .orElseThrow(() -> new RuntimeException("Dettaglio non trovato"));

        if (dettaglio.getOrdine().getStato_consegna() != Stato.IN_CORSO) {
            throw new RuntimeException("Non puoi modificare un ordine già confermato");
        }

        dettagliOrdineRepository.delete(dettaglio);
    }

    public void confermaOrdine(Long ordineId) {
        OrdineEntity ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"));

        if (ordine.getStato_consegna() != Stato.IN_CORSO) {
            throw new RuntimeException("L'ordine è già stato confermato");
        }

        for (DettaglioOrdineEntity dettaglio : ordine.getDettagliOrdine()) {
            aggiornaRimanenze(dettaglio);
        }

        ordine.setStato_consegna(Stato.ORDINATO);
        ordineRepository.save(ordine);
    }
    

    private void aggiornaRimanenze(DettaglioOrdineEntity dettaglio) {
        ProdottoEntity prodotto = dettaglio.getProdotto();
        if (prodotto.getRimanenza() < dettaglio.getQuantita()) {
            throw new RuntimeException("Rimanenze insufficienti per " + prodotto.getNome());
        }
        prodotto.setRimanenza(prodotto.getRimanenza() - dettaglio.getQuantita());
        if (prodotto.getRimanenza() == 0) {
            prodotto.setDisponibilita(false);
        }
        salvaProdotto(prodotto);
    }

    private void ripristinaRimanenze(DettaglioOrdineEntity dettaglio) {
        ProdottoEntity prodotto = dettaglio.getProdotto();
        prodotto.setRimanenza(prodotto.getRimanenza() + dettaglio.getQuantita());
        prodotto.setDisponibilita(true);
        salvaProdotto(prodotto);
    }

    private void salvaProdotto(ProdottoEntity prodotto) {
        if (prodotto instanceof CardEntity) {
            cardRepository.save((CardEntity) prodotto);
        } else if (prodotto instanceof BoxEntity) {
            boxRepository.save((BoxEntity) prodotto);
        } else if (prodotto instanceof BustinaEntity) {
            bustineRepository.save((BustinaEntity) prodotto);
        } else if (prodotto instanceof AccessoriEntity) {
            accessoriRepository.save((AccessoriEntity) prodotto);
        }
    }

    public void annullaOrdine(Long ordineId) {
        OrdineEntity ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"));

        if (ordine.getStato_consegna() == Stato.SPEDITO || ordine.getStato_consegna() == Stato.IN_CONSEGNA
                || ordine.getStato_consegna() == Stato.CONSEGNATO) {
            throw new RuntimeException("Non puoi annullare un ordine già spedito");
        }

        if (ordine.getStato_consegna() == Stato.ORDINATO) {
            for (DettaglioOrdineEntity dettaglio : ordine.getDettagliOrdine()) {
                ripristinaRimanenze(dettaglio);
            }
        }

        ordine.setStato_consegna(Stato.ANNULLATO);
        ordineRepository.save(ordine);
    }

    public double calcolaTotaleOrdine(Long ordineId) {
        return ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"))
                .getDettagliOrdine()
                .stream()
                .mapToDouble(dettaglio -> dettaglio.getPrezzo() * dettaglio.getQuantita())
                .sum();
    }
}

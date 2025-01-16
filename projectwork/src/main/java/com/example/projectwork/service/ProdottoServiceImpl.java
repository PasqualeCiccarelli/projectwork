package com.example.projectwork.service;



import java.util.Optional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.repository.ProdottoRepository;
import com.example.projectwork.service.interf.ProdottoService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProdottoServiceImpl implements ProdottoService {
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	public ProdottoDto getProdottoById(Long id) {
        ProdottoEntity prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto con ID " + id + " non trovato"));

        return new ProdottoDto().toDto(prodotto);
    }

	
	@Override
	public ProdottoDto modificaCategoriaProdotto(Long id) {
		
		ProdottoEntity prodotto=  prodottoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Prodotto con ID " + id + " non trovato"));
		
		prodotto.setCategoria(Categoria.SPECIALE);
		prodottoRepository.save(prodotto);
		
		return new ProdottoDto().toDto(prodotto);
	}
	
	@Scheduled(fixedRate = 86400000)
    public void aggiornaCategoriaProdotti() {
        List<ProdottoEntity> prodotti = prodottoRepository.findAll();

        for (ProdottoEntity prodotto : prodotti) {
            aggiornaCategoria(prodotto);
        }
    }

    private void aggiornaCategoria(ProdottoEntity prodotto) {
        if (prodotto.getCategoria() == Categoria.PREVENDITA) {
            if (prodotto.getDataInizio() != null && prodotto.getDataInizio().isBefore(LocalDate.now())) {
                prodotto.setCategoria(Categoria.NOVITA);
            }
        } else if (prodotto.getCategoria() == Categoria.NOVITA) {
            if (prodotto.getDataInizio() != null && Duration.between(prodotto.getDataInizio().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() >= 10) {
                prodotto.setCategoria(Categoria.DEFAULT);
            }
        }

        prodottoRepository.save(prodotto);
    }
	
	
	
}

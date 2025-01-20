package com.example.projectwork.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.AccessorioEntity;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.BustinaEntity;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Rarita;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.entity.entityenum.Tipo;
import com.example.projectwork.entity.entityenum.TipoCategoria;
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

	
	public ProdottoDto modificaCategoriaProdotto(Long id, Categoria categoria) {
	    ProdottoEntity prodotto = prodottoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Prodotto con ID " + id + " non trovato"));

	    prodotto.setCategoria(categoria);

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
    
    public Page<ProdottoDto> getProdottiFiltrati(
            TipoCategoria tipoCategoria,
            Rarita rarita,
            Categoria categoria,
            Stato stato,
            Brand brand,
            Double prezzoMinimo,
            Double prezzoMassimo,
            int page,
            int size) {
        
        Pageable pageable = PageRequest.of(page, size);

        Page<ProdottoEntity> prodotti = prodottoRepository.findAllWithFilters(
                tipoCategoria, rarita, categoria, stato, brand, prezzoMinimo, prezzoMassimo, pageable
        );

        return prodotti.map(ProdottoDto::toDto);
    }


	@Override
	public void eliminaProdottoUtente(Long id) {
		
		ProdottoEntity prodotto= prodottoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Prodotto con ID " + id + " non trovato"));
		
		prodottoRepository.delete(prodotto);
	}
	
	public List<ProdottoDto> searchProdotti(String query) {
        List<ProdottoEntity> prodotti = prodottoRepository.findByNomeContainingIgnoreCase(query);
        return prodotti.stream()
                .map(ProdottoDto::toDto)
                .collect(Collectors.toList());
    }
	
	 public List<ProdottoDto> getProdottiByBrandAndCategoria(Brand brand, Categoria categoria) {
	        List<ProdottoEntity> prodotti = prodottoRepository.findByBrandAndCategoria(brand, categoria);
	        return prodotti.stream().map(ProdottoDto::toDto).collect(Collectors.toList());
	    }
	 
	 public List<ProdottoDto> getProdottiByBrandTipoCategoriaAndTipi(Brand brand, TipoCategoria tipoCategoria, List<Tipo> tipi) {
	        List<ProdottoEntity> prodotti = prodottoRepository.findByBrandAndTipoCategoriaAndTipoIn(brand, tipoCategoria, tipi);
	        return prodotti.stream().map(ProdottoDto::toDto).collect(Collectors.toList());
	    }
	 
	 public List<ProdottoDto> findTop20SellingProductsByBrand(String brand) {
		    List<CardEntity> topCards = prodottoRepository.findTop5SellingCardsByBrand(brand);
		    List<BustinaEntity> topBustine = prodottoRepository.findTop5SellingBustineByBrand(brand);
		    List<BoxEntity> topBoxes = prodottoRepository.findTop5SellingBoxesByBrand(brand);
		    List<AccessorioEntity> topAccessori = prodottoRepository.findTop5SellingAccessoriByBrand(brand);

		    List<ProdottoDto> topProducts = new ArrayList<>();
		    
		    topProducts.addAll(topCards.stream()
		        .map(ProdottoDto::toDto)
		        .collect(Collectors.toList()));
		        
		    topProducts.addAll(topBustine.stream()
		        .map(ProdottoDto::toDto)
		        .collect(Collectors.toList()));
		        
		    topProducts.addAll(topBoxes.stream()
		        .map(ProdottoDto::toDto)
		        .collect(Collectors.toList()));
		        
		    topProducts.addAll(topAccessori.stream()
		        .map(ProdottoDto::toDto)
		        .collect(Collectors.toList()));

		    return topProducts;
		}
	
}

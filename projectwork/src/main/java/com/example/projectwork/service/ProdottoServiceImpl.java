package com.example.projectwork.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
}

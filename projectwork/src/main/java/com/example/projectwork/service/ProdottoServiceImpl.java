package com.example.projectwork.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.projectwork.entity.ProdottoEntity;
import com.example.projectwork.repository.ProdottoRepository;


@Service
public class ProdottoServiceImpl {
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	
//	public ProdottoDto getProdottoById(Long id) {
//		
//		ProdottoEntity prodotto= prodottoRepository.findById(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto non trovato!"));
//		
//		if(CardEntity)
//		
//		return null;
//	}
	
}

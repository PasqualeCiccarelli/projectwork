package com.example.projectwork.service.interf;

import com.example.projectwork.dto.ProdottoDto;

public interface ProdottoService {
	
	public ProdottoDto getProdottoById(Long id);
	public ProdottoDto modificaCategoriaProdotto(Long id);
}

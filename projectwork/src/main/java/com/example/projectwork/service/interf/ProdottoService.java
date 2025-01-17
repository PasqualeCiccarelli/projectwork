package com.example.projectwork.service.interf;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.entityenum.Categoria;

public interface ProdottoService {
	
	public ProdottoDto getProdottoById(Long id);
	public ProdottoDto modificaCategoriaProdotto(Long id, Categoria categoria);
}

package com.example.projectwork.service.interf;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;
import com.example.projectwork.entity.entityenum.Rarita;
import com.example.projectwork.entity.entityenum.Stato;
import com.example.projectwork.entity.entityenum.Tipo;
import com.example.projectwork.entity.entityenum.TipoCategoria;

public interface ProdottoService {
	
	public ProdottoDto getProdottoById(Long id);
	public ProdottoDto modificaCategoriaProdotto(Long id, Categoria categoria);
	public Page<ProdottoDto> getProdottiFiltrati(
            TipoCategoria tipoCategoria,
            Rarita rarita,
            Categoria categoria,
            Stato stato,
            Brand brand,
            Double prezzoMinimo,
            Double prezzoMassimo,
            int page,
            int size);
            
	public void eliminaProdottoUtente(Long id);
	public List<ProdottoDto> searchProdotti(String query);
	public List<ProdottoDto> getProdottiByBrandAndCategoria(Brand brand, Categoria categoria);
	public List<ProdottoDto> getProdottiByBrandTipoCategoriaAndTipi(Brand brand, TipoCategoria tipoCategoria, List<Tipo> tipi);
	 public List<ProdottoDto> findTop20SellingProductsByBrand(String brand);
}

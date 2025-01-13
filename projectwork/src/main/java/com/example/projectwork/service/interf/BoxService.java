package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.AccessoriDto;
import com.example.projectwork.dto.BoxDto;
import com.example.projectwork.dto.BoxRequest;
import com.example.projectwork.entity.BoxEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;


public interface BoxService {
	
	public BoxEntity creaBox(BoxRequest boxRequest);
	public List<BoxDto> getBoxByBrandPokemon();
	public List<BoxDto> getBoxByBrandMagic();
	public List<BoxDto> getBoxByBrandYugiho();
	public List<BoxDto> getCardByAccessori(Brand brand, Categoria categoria);

}

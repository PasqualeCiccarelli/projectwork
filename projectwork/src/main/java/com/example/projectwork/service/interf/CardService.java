package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.CardEntity;
import com.example.projectwork.entity.entityenum.Brand;
import com.example.projectwork.entity.entityenum.Categoria;

public interface CardService {
	
	public CardEntity creaCard(CardRequest cardRequest);
	public List<CardDto> getCardsByBrandPokemon();
	public List<CardDto> getCardsByBrandMagic();
	public List<CardDto> getCardsByBrandYugiho();
	public List<CardDto> getCardByCategoria(Brand brand, Categoria categoria);

}

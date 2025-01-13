package com.example.projectwork.service.interf;

import java.util.List;

import com.example.projectwork.dto.CardDto;
import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.CardEntity;

public interface CardService {
	
	public CardEntity creaCard(CardRequest cardRequest);
	public List<CardDto> getCardsByBrandPokemon();
	public List<CardDto> getCardsByBrandMagic();
	public List<CardDto> getCardsByBrandYugiho();
	

}

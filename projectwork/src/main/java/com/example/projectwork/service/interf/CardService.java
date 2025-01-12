package com.example.projectwork.service.interf;

import com.example.projectwork.dto.CardRequest;
import com.example.projectwork.entity.CardEntity;

public interface CardService {
	
	public CardEntity creaCard(CardRequest cardRequest);

}

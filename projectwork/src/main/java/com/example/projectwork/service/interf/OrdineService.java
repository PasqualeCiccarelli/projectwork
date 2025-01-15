package com.example.projectwork.service.interf;

import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.OrdineEntity;


public interface OrdineService {
	
	public OrdineDto aggiungiProdottoAlCarrello(String email, ProdottoDto prodottoDto, int quantita);
	public OrdineDto getOrdineInCorso(String email);
	public void eliminaOrdineInCorso(String email);
	public OrdineDto completaOrdine(String email, String indirizzo);
	public OrdineDto rimuoviProdottoDaCarrello(String email, Long dettaglioId, int quantita);

}

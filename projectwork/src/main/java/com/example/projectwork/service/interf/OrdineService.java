package com.example.projectwork.service.interf;

import com.example.projectwork.dto.DettagliOrdineDto;
import com.example.projectwork.dto.DettaglioOrdineRequest;
import com.example.projectwork.entity.OrdineEntity;


public interface OrdineService {
	
	public Long getUserIdByEmail(String email);
	public void aggiornaStatoOrdini();
	public DettagliOrdineDto aggiungiProdotto(Long userId, DettaglioOrdineRequest request);
	public void rimuoviDettaglio(Long dettaglioId);
	public void confermaOrdine(Long ordineId);
	public void annullaOrdine(Long ordineId);
	public double calcolaTotaleOrdine(Long ordineId);
	public OrdineEntity getOrdineInCorso(Long userId);

}

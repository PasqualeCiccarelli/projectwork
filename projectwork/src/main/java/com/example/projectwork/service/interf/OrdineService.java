package com.example.projectwork.service.interf;

import java.util.List;
import java.util.Optional;

import com.example.projectwork.dto.OrdineDto;
import com.example.projectwork.dto.ProdottoDto;
import com.example.projectwork.entity.entityenum.Stato;


public interface OrdineService {
	
	public OrdineDto aggiungiProdottoAlCarrello(String email, ProdottoDto prodottoDto, int quantita);
	public OrdineDto getOrdineInCorso(String email);
	public void eliminaOrdineInCorso(String email);
	public OrdineDto completaOrdine(String email, String indirizzo);
	public OrdineDto rimuoviProdottoDaCarrello(String email, Long dettaglioId, int quantita);
	public List<OrdineDto> getOrdiniByStatoAndEmail(String email, Stato... stati);
	public List<OrdineDto> getOrdiniInArrivo(String email);
	public List<OrdineDto> getOrdiniConsegnati(String email);
	public Optional<OrdineDto> findOrdineByUtenteAndStato(String email, Stato stato);
	public void eliminaOrdine(String email, Long ordineId);

}

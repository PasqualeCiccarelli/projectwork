package com.example.projectwork.mvcctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class HtmlCtrl {
	
	@GetMapping("/")
	public String miaHome() {
		return "home";
	}
	
	@GetMapping("/login")
	public String mioLogin() {
		return "login";
	}
	
	@GetMapping("/registrati")
	public String mioRegistrati() {
		return "registrazione";
	}
	
	@GetMapping("/promozione")
	public String mioPromozione() {
		return "promozione";
	}

	@GetMapping("/creazione-card")
	public String miaCreazioneCard() {
		return "creazionecarta";
	}
	
	@GetMapping("/creazione-accessori")
	public String miaCreazioneAccessori() {
		return "creazioneAccessori";
	}
	
	@GetMapping("/creazione-bustine")
	public String miaCreazioneBustine() {
		return "creazioneBustine";
	}
	
	@GetMapping("/creazione-box")
	public String miaCreazioneBox() {
		return "creazioneBox";
	}
	
	@GetMapping("/negozio-pokemon")
	public String mioNegozioPokemon() {
		return "negozioPokemon";
	}
	
	@GetMapping("/negozio-magic")
	public String mioNegozioMagic() {
		return "negozioMagic";
	}
	
	@GetMapping("/negozio-yugiho")
	public String mioNegozioYuGiHo() {
		return "negozioYuGiHo";
	}
	
	@GetMapping("/carrello")
	public String mioCarrello() {
		return "carrello";
	}

	@GetMapping("/pagina-admin")
	public String paginaAdmin(){
		return "admin";
	}
	
	@GetMapping("/utente")
	public String paginaOrdini(){
		return "utente";
	}
	
	@GetMapping("/negozio")
	public String paginaNegozio(){
		return "negozio";
	}
	
	@GetMapping("/chiSiamo")
	public String paginaChiSiamo(){
		return "chiSiamo";
	}

	@GetMapping("/politiche")
	public String paginaPolitiche(){
		return "politiche-aziendali";
	}
	
	@GetMapping("/errore")
	public String paginaErrore(){
		return "errore";
	}
}

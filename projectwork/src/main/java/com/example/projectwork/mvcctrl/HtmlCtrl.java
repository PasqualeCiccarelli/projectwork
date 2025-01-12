package com.example.projectwork.mvcctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

package com.example.projectwork.restCtrl;



import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/status")
	public ResponseEntity<?> getAuthStatus() {
	    try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
	            // Log dell'utente
	            logger.info("Utente autenticato: {}", auth.getName());
	            Map<String, Object> response = new HashMap<>();
	            response.put("email", auth.getName());
	            response.put("roles", auth.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList()));
	            return ResponseEntity.ok(response);
	        }
	        logger.info("Nessun utente autenticato");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    } catch (Exception e) {
	        logger.error("Errore durante il controllo dello stato di autenticazione", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Errore durante il controllo dello stato di autenticazione");
	    }
	}


}

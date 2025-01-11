package com.example.projectwork.restCtrl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.projectwork.dto.LoginRequest;
import com.example.projectwork.dto.LoginResponse;
import com.example.projectwork.dto.RegistrazioneRequest;
import com.example.projectwork.service.interf.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationCtrl {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authenticationService.login(loginRequest));
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegistrazioneRequest registerRequest) {
		authenticationService.registrazione(registerRequest);
		return ResponseEntity.ok("Registrazione avvenuta con successo");
	}

	@PostMapping("/promote/{userId}")
	public ResponseEntity<String> promoteToAdmin(@PathVariable Long userId, @RequestParam boolean acceptedPolicies) {

		authenticationService.promozioneToAdmin(userId, acceptedPolicies);
		return ResponseEntity.ok("Utente promosso a partner con successo");
	}

}

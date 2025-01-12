package com.example.projectwork.restCtrl;



import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/status")
    public ResponseEntity<?> getAuthStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Map<String, Object> status = new HashMap<>();
            status.put("email", auth.getName());
            status.put("roles", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
            return ResponseEntity.ok(status);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authenticationService.login(loginRequest);
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

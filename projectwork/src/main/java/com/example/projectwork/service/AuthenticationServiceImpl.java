package com.example.projectwork.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.projectwork.dto.LoginRequest;
import com.example.projectwork.dto.LoginResponse;
import com.example.projectwork.dto.RegistrazioneRequest;
import com.example.projectwork.dto.RegistrazioneResponse;
import com.example.projectwork.entity.AdminEntity;
import com.example.projectwork.entity.UtenteEntity;
import com.example.projectwork.entity.entityenum.Ruolo;
import com.example.projectwork.repository.AdminRepository;
import com.example.projectwork.repository.UtenteRepository;
import com.example.projectwork.service.interf.AuthenticationService;
import com.example.projectwork.util.PasswordUtil;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public LoginResponse login(LoginRequest loginRequest) {

	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();

	    Optional<UtenteEntity> utente = utenteRepository.findByEmail(email);
	    Optional<AdminEntity> admin = adminRepository.findByEmail(email);

	    String ruolo1 = null;
	    String ruolo2 = null;
	    Long id = null;

	    if (utente.isPresent() && PasswordUtil.verifyPassword(password, utente.get().getPassword())) {
	        ruolo1 = utente.get().getRuolo().name();
	        id = utente.get().getId();
	    }

	    if (admin.isPresent() && PasswordUtil.verifyPassword(password, admin.get().getPassword())) {
	        ruolo2 = admin.get().getRuolo().name();
	        id = admin.get().getId();
	    }

	    if (ruolo1 == null && ruolo2 == null) {
	        throw new ResponseStatusException(UNAUTHORIZED, "Credenziali non valide");
	    }
	    
	    Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    String nome = utente.map(UtenteEntity::getNome)
	            .orElse(admin.map(AdminEntity::getNome).orElse(null));

	    return new LoginResponse(nome, ruolo1, ruolo2, email, id);
	}
	
	public RegistrazioneResponse registrazione(RegistrazioneRequest registerRequest) {
	    Optional<UtenteEntity> existingUser = utenteRepository.findByEmail(registerRequest.getEmail());

	    if (existingUser.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email già in uso");
	    }

	    if (!registerRequest.isAdult()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'età minima per la registrazione è di 16 anni");
	    }

	    if (!registerRequest.isAccettaPolitiche()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Devi accettare le politiche aziendali per registrarti");
	    }

	    UtenteEntity newUser = new UtenteEntity();
	    newUser.setNome(registerRequest.getNome());
	    newUser.setCognome(registerRequest.getCognome());
	    newUser.setEmail(registerRequest.getEmail());
	    newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	    newUser.setData_nascita(registerRequest.getDataNascita());
	    newUser.setRuolo(Ruolo.UTENTE);

	    UtenteEntity savedUser = utenteRepository.save(newUser);

	    return new RegistrazioneResponse(savedUser.getId(), savedUser.getNome(), savedUser.getEmail(), savedUser.getRuolo().name());
	}

	public LoginResponse promozioneToAdmin(Long userId, boolean acceptedPolicies) {

	    UtenteEntity utente = utenteRepository.findById(userId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

	    if (!isUserAdult(utente.getData_nascita())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utente deve avere più di 18 anni per diventare partner");
	    }

	    if (!acceptedPolicies) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utente deve aver accettato le politiche aziendali");
	    }

	    AdminEntity newAdmin = new AdminEntity();
	    newAdmin.setNome(utente.getNome());
	    newAdmin.setCognome(utente.getCognome());
	    newAdmin.setEmail(utente.getEmail());
	    newAdmin.setPassword(utente.getPassword());
	    newAdmin.setData_nascita(utente.getData_nascita());
	    newAdmin.setRuolo(Ruolo.ADMIN);

	    adminRepository.save(newAdmin);

	    AdminEntity admin = adminRepository.findByEmail(newAdmin.getEmail())
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Errore durante la creazione dell'admin"));

	    
	    String email = admin.getEmail();
	    String ruolo1 = utente.getRuolo().name();
	    String ruolo2 = admin.getRuolo().name();
	    Long id = admin.getId();
	    String nome = admin.getNome();

	    System.out.println("\n\nLoginResponse: "+email+" "+ruolo1+" "+ruolo2+" "+id+" "+nome+"\n\n");
	    
	    return new LoginResponse(nome, ruolo1, ruolo2, email, id);
	}
	 
	 private boolean isUserAdult(LocalDate birthDate) {
		 
	        LocalDate today = LocalDate.now();
	        int age = today.getYear() - birthDate.getYear();
	        if (today.getDayOfYear() < birthDate.getDayOfYear()) {
	            age--;
	        }
	        return age >= 18;
	 }

}

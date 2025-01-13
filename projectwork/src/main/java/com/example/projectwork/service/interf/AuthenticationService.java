package com.example.projectwork.service.interf;

import com.example.projectwork.dto.LoginRequest;
import com.example.projectwork.dto.LoginResponse;
import com.example.projectwork.dto.RegistrazioneRequest;
import com.example.projectwork.dto.RegistrazioneResponse;

public interface AuthenticationService {
	
	public LoginResponse login(LoginRequest loginRequest);
	public RegistrazioneResponse registrazione(RegistrazioneRequest registerRequest);
	public LoginResponse promozioneToAdmin(Long userId, boolean acceptedPolicies);

}

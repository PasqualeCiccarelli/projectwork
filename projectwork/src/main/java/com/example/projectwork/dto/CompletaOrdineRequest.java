package com.example.projectwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletaOrdineRequest {

	private String email;
    private String indirizzo;
    
    public CompletaOrdineRequest() {
		// TODO Auto-generated constructor stub
	}

	public CompletaOrdineRequest(String email, String indirizzo) {
		super();
		this.email = email;
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	} 
    
}

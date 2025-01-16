package com.example.projectwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EliminaOrdineRequest {
	
	private String email;
	
	public EliminaOrdineRequest() {
		// TODO Auto-generated constructor stub
	}

	public EliminaOrdineRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

package com.example.projectwork.eccezioni;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private String message;

	    public ErrorResponse(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
    
    
}

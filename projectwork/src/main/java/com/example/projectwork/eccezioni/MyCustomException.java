package com.example.projectwork.eccezioni;

public class MyCustomException extends RuntimeException{

	 private int errorCode;

	    public MyCustomException(String message, int errorCode) {
	        super(message);
	        this.errorCode = errorCode;
	    }

	    public int getErrorCode() {
	        return errorCode;
	    }
}

package com.eygds.ams.exception;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 9093104731534881360L;

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String message) {
		super(message);
	}
}

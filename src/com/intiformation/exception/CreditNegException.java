package com.intiformation.exception;

public class CreditNegException extends Exception {

	public CreditNegException() {
		System.err.println("Valeur de crédit négative.");
	}
}

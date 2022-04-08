package com.intiformation.exception;

public class DepassementCreditException extends Exception {

	public DepassementCreditException() {
		System.err.println("Paiement supérieur au crédit.");
	}
}

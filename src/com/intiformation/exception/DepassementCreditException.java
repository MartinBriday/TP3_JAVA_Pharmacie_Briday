package com.intiformation.exception;

public class DepassementCreditException extends Exception {

	public DepassementCreditException() {
		System.err.println("Paiement sup�rieur au cr�dit.");
	}
}

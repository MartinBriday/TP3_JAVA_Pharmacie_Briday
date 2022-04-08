package com.intiformation.exception;

public class PaiementNegException extends Exception {

	public PaiementNegException() {
		System.err.println("Valeur de paiement négative.");
	}
}

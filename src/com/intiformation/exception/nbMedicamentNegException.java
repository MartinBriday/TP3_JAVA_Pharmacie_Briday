package com.intiformation.exception;

public class nbMedicamentNegException extends Exception {

	public nbMedicamentNegException() {
		System.err.println("Quantité de médicaments négative.");
	}
}

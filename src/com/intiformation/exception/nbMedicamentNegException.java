package com.intiformation.exception;

public class nbMedicamentNegException extends Exception {

	public nbMedicamentNegException() {
		System.err.println("Quantit� de m�dicaments n�gative.");
	}
}

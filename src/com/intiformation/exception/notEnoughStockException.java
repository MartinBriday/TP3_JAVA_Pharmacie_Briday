package com.intiformation.exception;

public class notEnoughStockException extends Exception {

	public notEnoughStockException() {
		System.err.println("Stock de médicament insuffisant.");
	}
}

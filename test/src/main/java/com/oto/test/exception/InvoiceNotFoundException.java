package com.oto.test.exception;

public class InvoiceNotFoundException extends Exception {

	public InvoiceNotFoundException(String message) {
		System.out.println("Error : "+message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 289993079974539939L;
	

}

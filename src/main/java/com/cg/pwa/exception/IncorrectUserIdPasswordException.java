package com.cg.pwa.exception;

public class IncorrectUserIdPasswordException extends Exception {
   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;

	public IncorrectUserIdPasswordException(String string) {
		this.string=string;
		
	}

	@Override
	public String toString() {
		return "IncorrectUserIdPasswordException [message =" + string + "]";
	}
	
	
}

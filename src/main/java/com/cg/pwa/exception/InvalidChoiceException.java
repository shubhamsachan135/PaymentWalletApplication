package com.cg.pwa.exception;

public class InvalidChoiceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;

	public InvalidChoiceException(String string) {
		this.string=string;
		
	}

	@Override
	public String toString() {
		return "InvalidChoiceException [message =" + string + "]";
	}
	
	
}

package com.cg.pwa.exception;

public class LowBalanceException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;

	public LowBalanceException(String string) {
		this.string=string;
		
	}

	@Override
	public String toString() {
		return "LowBalanceException [message =" + string + "]";
	}
	
	
	
	
}

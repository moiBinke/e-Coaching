package com.novem.cours.exceptions;

public class CycleException extends Exception{

	public CycleException() {
		super();
	}

	public CycleException(String arg0) {
		super(arg0);
	}
	
	public static class CycleNameExistException extends CycleException{

		public CycleNameExistException() {
			super();
			
		}

		public CycleNameExistException(String arg0) {
			super(arg0);
			
		}
		
	}

}

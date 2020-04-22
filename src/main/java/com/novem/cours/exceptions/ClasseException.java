package com.novem.cours.exceptions;

public class ClasseException extends Exception{

	

	public ClasseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClasseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static class ClasseNameExistException extends ClasseException {

		public ClasseNameExistException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ClasseNameExistException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class ClasseHasNoCycleException extends ClasseException {

		public ClasseHasNoCycleException() {
			super();
		}

		public ClasseHasNoCycleException(String message) {
			super(message);
		}
		
		
	}
}

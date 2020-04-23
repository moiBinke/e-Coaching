package com.novem.cours.exceptions;

public class LeconException extends Exception {

	public LeconException() {
		super();
	}

	public LeconException(String message) {
		super(message);
	}
	

	public static class LeconNameExistException extends Exception{

		public LeconNameExistException() {
			super();
		}

		public LeconNameExistException(String message) {
			super(message);
		}
		
	}
	public static class LeconMatiereNotFound extends Exception{

		public LeconMatiereNotFound() {
			super();
		}

		public LeconMatiereNotFound(String message) {
			super(message);
		}
		
	}
	
	
	

	
}

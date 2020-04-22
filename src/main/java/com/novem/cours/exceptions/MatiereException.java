package com.novem.cours.exceptions;

import com.novem.cours.exceptions.ClasseException.ClasseNameExistException;

public class MatiereException extends Exception {

	public MatiereException() {
		super();
	}

	public MatiereException(String message) {
		super(message);
	}

	public static class MatiereNameExistException extends MatiereException{

		public MatiereNameExistException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MatiereNameExistException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class MatiereClassNotFoundException extends MatiereException{

		public MatiereClassNotFoundException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MatiereClassNotFoundException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
}
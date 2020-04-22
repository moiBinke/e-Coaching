package com.novem.cours.exceptions;

public class OtherExceptions extends Exception{

	public OtherExceptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtherExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static class EmailNotCorrectException extends OtherExceptions{

		public EmailNotCorrectException() {
			super();
		}

		public EmailNotCorrectException(String message) {
			super(message);
		}
		
	}
	public static class motDePasseIsNotCorrect extends OtherExceptions{

		public motDePasseIsNotCorrect() {
			super();
			// TODO Auto-generated constructor stub
		}

		public motDePasseIsNotCorrect(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	}
}

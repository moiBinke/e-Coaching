package com.novem.cours.exceptions;

public class PartieException extends Exception{

	public PartieException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartieException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public static class PartieNameExistException extends PartieException{

		public PartieNameExistException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PartieNameExistException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
	public static class PartieLeconNotFound extends PartieException{

		public PartieLeconNotFound() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PartieLeconNotFound(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
		
	}
	public static class PartieContenuException extends PartieException{

		public PartieContenuException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PartieContenuException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}

	
}

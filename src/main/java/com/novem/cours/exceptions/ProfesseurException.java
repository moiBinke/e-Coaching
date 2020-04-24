package com.novem.cours.exceptions;

public class ProfesseurException extends Exception{

	public ProfesseurException() {
		super();
	}

	public ProfesseurException(String message) {
		super(message);
	}

	public static class ProfesseurEmailExist extends ProfesseurException{

		public ProfesseurEmailExist() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProfesseurEmailExist(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class ProfesseurEmailNotCorrect extends ProfesseurException{

		public ProfesseurEmailNotCorrect() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProfesseurEmailNotCorrect(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	}
	
	public static class ProfesseurClassNotFoun extends ProfesseurException{

		public ProfesseurClassNotFoun() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProfesseurClassNotFoun(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class ProfesseurClassDejaAffecte extends ProfesseurException{

		public ProfesseurClassDejaAffecte() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProfesseurClassDejaAffecte(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}

	public static class ProfesseurWrongPassword extends ProfesseurException{

		public ProfesseurWrongPassword() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProfesseurWrongPassword(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
	}


}

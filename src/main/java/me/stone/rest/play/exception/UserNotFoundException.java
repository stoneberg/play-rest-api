package me.stone.rest.play.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1947411415252441147L;

	public UserNotFoundException(String message) {
		super(message);
	}

}

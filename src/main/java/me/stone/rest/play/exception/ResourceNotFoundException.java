package me.stone.rest.play.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 6839076383292082466L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}

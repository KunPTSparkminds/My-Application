package net.sparkminds.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}

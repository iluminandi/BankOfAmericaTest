package org.boa.exception;

/**
 * An exception thrown when the validation of the data submitted by the client fails
 * 
 * @author lukas
 */
public class ClientDataException extends RuntimeException {

	private static final long serialVersionUID = 3399301099846462260L;

	public ClientDataException() {
		super();
	}

	public ClientDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientDataException(String message) {
		super(message);
	}
	
}

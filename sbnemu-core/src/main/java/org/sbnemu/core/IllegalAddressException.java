package org.sbnemu.core;

public class IllegalAddressException extends RuntimeException {

	public IllegalAddressException() {
	}

	public IllegalAddressException(String message) {
		super(message);
	}

	public IllegalAddressException(Throwable cause) {
		super(cause);
	}

	public IllegalAddressException(String message, Throwable cause) {
		super(message, cause);
	}

}

package org.sbnemu.core.ex;

import org.sbnemu.core.Memory;

/**
 * Exception thrown when setting a {@link Memory} address to a value
 * that is not supported
 * @author robin
 *
 */
public class IllegalDataException extends RuntimeException {

	public IllegalDataException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

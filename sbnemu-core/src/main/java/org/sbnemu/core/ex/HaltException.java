package org.sbnemu.core.ex;

/**
 * Exception thrown when a device wishes the virtual machine to halt
 * @author robin
 *
 */
public class HaltException extends RuntimeException {

	public HaltException() {
		// TODO Auto-generated constructor stub
	}

	public HaltException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public HaltException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public HaltException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

package org.sbnemu.core.ex;

import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;
import org.sbnemu.core.Memory;

/**
 * Exception thrown when a {@link Device}'s {@link Memory} methods are accessed
 * outside of the address range of the {@link Device}.  Also thrown if other
 * operations, such as adding a {@link Device} to a {@link Bus}, would cause
 * an address conflict.
 * @author robin
 *
 */
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

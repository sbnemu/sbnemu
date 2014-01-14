package org.sbnemu.core.ex;

/**
 * Exception thrown to cause a user-defined interrupt to be issued.
 * Interrupts have a one-byte ID.
 * @author robin
 *
 */
public class InterruptException extends RuntimeException {

	/**
	 * The ID of the interrupt
	 */
	protected byte interruptId;
	
	public InterruptException(byte interruptId) {
		this.interruptId = interruptId;
	}

	public InterruptException(byte interruptId, String message) {
		super(message);
		this.interruptId = interruptId;
	}

	public InterruptException(byte interruptId, Throwable cause) {
		super(cause);
		this.interruptId = interruptId;
	}

	public InterruptException(byte interruptId, String message, Throwable cause) {
		super(message, cause);
		this.interruptId = interruptId;
	}

	/**
	 * Return the ID of this {@link InterruptException}
	 * @return
	 */
	public byte getInterruptId() {
		return interruptId;
	}
}

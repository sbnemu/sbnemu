package org.sbnemu.core.ex;

public class InterruptException extends RuntimeException {

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

	public byte getInterruptId() {
		return interruptId;
	}

	public void setInterruptId(byte interruptId) {
		this.interruptId = interruptId;
	}

}

package org.sbnemu.core;

/**
 * An object that can act as a memory source, which means it can get
 * and set values at addresses
 * @author robin
 *
 */
public interface Memory {
	/**
	 * Get the value at the argument address
	 * @param address The address of the value
	 * @return The value at that address
	 */
	public long get(long address);
	/**
	 * Set the value at an address
	 * @param address The address of the value to set
	 * @param value The value to set
	 */
	public void set(long address, long value);
}

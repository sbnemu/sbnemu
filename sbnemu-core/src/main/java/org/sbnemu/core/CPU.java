package org.sbnemu.core;

import org.sbnemu.core.dev.ExceptionVectorTable;

/**
 * Interface for the single-instruction CPU
 * @author robin
 *
 */
public interface CPU {
	/**
	 * Perform a single SBN instruction
	 */
	public void tick();
	
	/**
	 * Return the program counter
	 * @return
	 */
	public long getPc();
	/**
	 * Set the program counter
	 * @param pc
	 */
	public void setPc(long pc);
	
	/**
	 * Return the bus for memory access
	 * @return The bus used for memory access
	 */
	public Bus getBus();
	/**
	 * Set the bus for memory access
	 * @param bus
	 */
	public void setBus(Bus bus);
}


package org.sbnemu.core;

import org.sbnemu.core.ex.IllegalAddressException;

/**
 * A {@link Memory} that delegates its access to added sub {@link Device}s
 * @author robin
 *
 */
public interface Bus extends Memory {
	/**
	 * Add a {@link Device} to this {@link Bus}
	 * @param device The device to add
	 */
	public void addDevice(Device device);
	/**
	 * Remove a {@link Device} from this {@link Bus}
	 * @param device The device to remove
	 */
	public void removeDevice(Device device);
	
	public Memory getDefaultMemory();
	
	public void setDefaultMemory(Memory defaultMemory);
}

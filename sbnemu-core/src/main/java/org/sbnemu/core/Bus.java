package org.sbnemu.core;

import org.sbnemu.core.ex.IllegalAddressException;

/**
 * A {@link Memory} that delegates its access to added sub {@link Device}s
 * @author robin
 *
 */
public interface Bus extends Device {
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
	
	/**
	 * Return all the devices on this bus.  Is not recurasive, so
	 * if a bus is attached to a bus, the secondary bus's devices will
	 * not be listed.
	 * @return
	 */
	public Device[] getDevices();
	
	/**
	 * Set the devices on this bus
	 * @param devices The new set of devices for the bus
	 */
	public void setDevices(Device... devices);
	
	/**
	 * Returns the default {@link Memory} to be used if no device handles a particular address
	 * @return The default {@link Memory}
	 */
	public Memory getDefaultMemory();
	
	/**
	 * Sets the default {@link Memory} to be used if no device handles a particular address
	 * @param defaultMemory The new default {@link Memory}
	 */
	public void setDefaultMemory(Memory defaultMemory);
}

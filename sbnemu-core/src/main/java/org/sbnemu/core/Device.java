package org.sbnemu.core;

/**
 * A device that can be attached to a {@link Bus}.  {@link Device}s are
 * a type of {@link Memory} that have a specific set of valid address ranges.
 * @author robin
 *
 */
public interface Device extends Memory {
	/**
	 * Return the address ranges for this device
	 * @return
	 */
	public AddressRange[] getAddresses();
	/**
	 * Set the address ranges for this device
	 * @param addresses
	 */
	public void setAddresses(AddressRange... addresses);
	
	/**
	 * Add an {@link AddressListener} to this device
	 * @param l
	 */
	public void addAddressListener(AddressListener l);
	/**
	 * Remove an {@link AddressListener} from this device
	 * @param l
	 */
	public void removeAddressListener(AddressListener l);
}

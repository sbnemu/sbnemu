package org.sbnemu.core;

import java.util.EventObject;

/**
 * Event issued by a {@link Device} when its addresses are set
 * @author robin
 *
 */
public class AddressEvent extends EventObject {

	/**
	 * The original address array
	 */
	protected AddressRange[] oldAddresses;
	/**
	 * The new address array
	 */
	protected AddressRange[] newAddresses;
	
	/**
	 * Create a new {@link AddressEvent}
	 * @param source The {@link Device} whose addresses were changed
	 * @param oldAddresses The old addresses
	 * @param newAddresses The new addresses
	 */
	public AddressEvent(Device source, AddressRange[] oldAddresses, AddressRange[] newAddresses) {
		super(source);
		this.oldAddresses = oldAddresses;
		this.newAddresses = newAddresses;
	}

	@Override
	public Device getSource() {
		return (Device) super.getSource();
	}

	/**
	 * Returns the old addresses for the device
	 * @return
	 */
	public AddressRange[] getOldAddresses() {
		return oldAddresses;
	}
	
	/**
	 * Returns the new addresses for the device
	 * @return
	 */
	public AddressRange[] getNewAddresses() {
		return newAddresses;
	}
	
}

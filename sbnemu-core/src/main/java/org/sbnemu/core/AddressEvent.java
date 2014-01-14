package org.sbnemu.core;

import java.util.EventObject;

public class AddressEvent extends EventObject {

	protected AddressRange[] oldAddresses;
	protected AddressRange[] newAddresses;
	
	public AddressEvent(Device source, AddressRange[] oldAddresses, AddressRange[] newAddresses) {
		super(source);
		this.oldAddresses = oldAddresses;
		this.newAddresses = newAddresses;
	}

	@Override
	public Device getSource() {
		return (Device) super.getSource();
	}

	public AddressRange[] getOldAddresses() {
		return oldAddresses;
	}
	
	public AddressRange[] getNewAddresses() {
		return newAddresses;
	}
	
}

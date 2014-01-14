package org.sbnemu.core;

public interface Device extends Memory {
	public AddressRange[] getAddresses();
	public void setAddresses(AddressRange... addresses);
	
	public void addAddressListener(AddressListener l);
	public void removeAddressListener(AddressListener l);
}

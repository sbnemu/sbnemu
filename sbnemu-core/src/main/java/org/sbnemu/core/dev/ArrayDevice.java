package org.sbnemu.core.dev;

import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

public class ArrayDevice extends AbstractDevice {

	protected long[] data;
	
	public ArrayDevice() {
		this(0);
	}
	
	public ArrayDevice(int size) {
		data = new long[size];
	}
	
	public ArrayDevice(long[] data) {
		this.data = data;
	}

	public void setAddresses(AddressRange... addresses) {
		if(addresses.length != 1)
			throw new IllegalAddressException();
		super.setAddresses(addresses);
		data = Arrays.copyOf(data, (int)(getEndAddress() - getBaseAddress()));
	}
	
	protected long getBaseAddress() {
		return getAddresses()[0].getBaseAddress();
	}
	
	protected long getEndAddress() {
		return getAddresses()[0].getEndAddress();
	}
	
	public long get(long address) {
		validateAddress(address);
		return data[(int)(address - getBaseAddress())];
	}
	
	public void set(long address, long value) {
		validateAddress(address);;
		data[(int)(address - getBaseAddress())] = value;
	}
	
}

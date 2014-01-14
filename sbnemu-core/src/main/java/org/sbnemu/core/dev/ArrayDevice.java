package org.sbnemu.core.dev;

import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

public class ArrayDevice implements Device {

	protected long baseAddress;
	protected long endAddress;
	
	protected long[] data;
	
	public ArrayDevice(int size) {
		data = new long[size];
	}

	public AddressRange[] getAddresses() {
		return new AddressRange[] { new AddressRange(baseAddress, endAddress) };
	}
	
	public void setAddresses(AddressRange... addresses) {
		if(addresses.length != 1)
			throw new IllegalAddressException();
		baseAddress = addresses[0].getBaseAddress();
		endAddress = addresses[0].getEndAddress();
		data = Arrays.copyOf(data, (int)(endAddress - baseAddress));
	}
	
	public long getBaseAddress() {
		return baseAddress;
	}
	
	public long getEndAddress() {
		return endAddress;
	}
	
	public long get(long address) {
		if(address < baseAddress || address >= endAddress)
			throw new IllegalAddressException();
		return data[(int)(address - baseAddress)];
	}
	
	public void set(long address, long value) {
		if(address < baseAddress || address >= endAddress)
			throw new IllegalAddressException();
		data[(int)(address - baseAddress)] = value;
	}
	
}

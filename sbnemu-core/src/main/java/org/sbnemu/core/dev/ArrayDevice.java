package org.sbnemu.core.dev;

import org.sbnemu.core.Device;
import org.sbnemu.core.IllegalAddressException;

public class ArrayDevice implements Device {

	protected long baseAddress;
	protected long endAddress;
	
	protected long[] data;
	
	public long getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(long baseAddress) {
		this.baseAddress = baseAddress;
	}
	public long getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(long endAddress) {
		this.endAddress = endAddress;
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

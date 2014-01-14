package org.sbnemu.core;

public class AddressRange {

	protected long baseAddress;
	protected long endAddress;
	
	public AddressRange(long baseAddress, long endAddress) {
		this.baseAddress = baseAddress;
		this.endAddress = endAddress;
	}

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

}

package org.sbnemu.core;

public class AddressRange implements Comparable<AddressRange> {

	private long baseAddress;
	private long endAddress;
	
	public AddressRange(long baseAddress, long endAddress) {
		this.baseAddress = baseAddress;
		this.endAddress = endAddress;
	}

	public AddressRange(long address) {
		this(address, address);
	}
	
	public long getBaseAddress() {
		return baseAddress;
	}

	public long getEndAddress() {
		return endAddress;
	}

	public boolean contains(long address) {
		return baseAddress <= address && address < endAddress;
	}
	
	public boolean intersects(AddressRange r) {
		if(baseAddress >= r.baseAddress && baseAddress < endAddress)
			return true;
		if(endAddress > r.baseAddress && endAddress <= r.endAddress)
			return true;
		if(baseAddress < r.baseAddress && endAddress > r.endAddress)
			return true;
		if(baseAddress > r.baseAddress && endAddress < r.endAddress)
			return true;
		return false;
	}

	public int compareTo(AddressRange o) {
		if(baseAddress < o.baseAddress && endAddress < o.endAddress)
			return -1;
		if(baseAddress > o.baseAddress && endAddress > o.endAddress)
			return 1;
		return 0;
	}
	
	@Override
	public int hashCode() {
		return (int)(baseAddress * endAddress);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj instanceof AddressRange) {
			AddressRange a = (AddressRange) obj;
			return baseAddress == a.baseAddress && endAddress == a.endAddress;
		}
		return false;
	}

}

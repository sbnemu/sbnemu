package org.sbnemu.core;

/**
 * A range of addresses, starting with (inclusive) a base address
 * and ending with (exclusive) an end address.
 * @author robin
 *
 */
public class AddressRange implements Comparable<AddressRange> {

	/**
	 * The base address
	 */
	private long baseAddress;
	/**
	 * The end address
	 */
	private long endAddress;
	
	// Required for deserialization
	@Deprecated
	public AddressRange() {}
	
	/**
	 * Create a new {@link AddressRange}
	 * @param baseAddress The base address
	 * @param endAddress The end address
	 */
	public AddressRange(long baseAddress, long endAddress) {
		this.baseAddress = baseAddress;
		this.endAddress = endAddress;
	}

	/**
	 * Returns the base address
	 * @return
	 */
	public long getBaseAddress() {
		return baseAddress;
	}

	/**
	 * Returns the end address
	 * @return
	 */
	public long getEndAddress() {
		return endAddress;
	}

	/**
	 * Returns the size of this address range
	 * @return
	 */
	public long getExtent() {
		return endAddress - baseAddress;
	}
	
	/**
	 * Returns whether this address range contains the argument address
	 * @param address
	 * @return {@code true} if the address is contained in this range
	 */
	public boolean contains(long address) {
		return baseAddress <= address && address < endAddress;
	}
	
	/**
	 * Returns whether this {@link AddressRange} intersects the argument range
	 * @param r The range to check for intersection
	 * @return {@code true} if the ranges intersect
	 */
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

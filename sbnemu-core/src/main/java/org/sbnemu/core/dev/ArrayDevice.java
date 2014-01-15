package org.sbnemu.core.dev;

import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

/**
 * Memory device backed by an array of {@code long}.  {@link ArrayDevice}
 * supports only a single {@link AddressRange}
 * @author robin
 *
 */
public class ArrayDevice extends AbstractDevice {

	/**
	 * The array of longs
	 */
	protected long[] data;
	
	/**
	 * Create an {@link ArrayDevice} with zero extent
	 */
	public ArrayDevice() {
		this(0);
	}
	
	/**
	 * Create an {@link ArrayDevice} with the specified size
	 * @param size
	 */
	public ArrayDevice(int size) {
		data = new long[size];
	}
	
	/**
	 * Create an {@link ArrayDevice} that <b>wraps</b> the argument
	 * array.  Changes to the array will be reflected in the device.
	 * @param data
	 */
	public ArrayDevice(long[] data) {
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * The argument must be an array of exactly one {@link AddressRange}.
	 */
	public void setAddresses(AddressRange... addresses) {
		if(addresses.length != 1)
			throw new IllegalAddressException();
		super.setAddresses(addresses);
		data = Arrays.copyOf(data, (int)(getEndAddress() - getBaseAddress()));
	}
	
	/**
	 * Return the base address of the single {@link AddressRange}
	 * @return
	 */
	protected long getBaseAddress() {
		return getAddresses()[0].getBaseAddress();
	}
	
	/**
	 * Return the end address of the single {@link AddressRange}
	 * @return
	 */
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
	
	@Override
	protected String toStringData() {
		return ",size:" + data.length;
	}
}

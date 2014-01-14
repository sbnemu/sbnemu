package org.sbnemu.core.dev;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.ex.IllegalAddressException;

public class ByteBufferDevice extends AbstractDevice {

	protected ByteBuffer buf;
	protected int position;
	protected int limit;
	
	public ByteBufferDevice(ByteBuffer buf) {
		this(buf, buf.position(), buf.limit());
	}
	
	public ByteBufferDevice(ByteBuffer buf, int position, int limit) {
		this.buf = buf;
		buf.position(position);
		buf.limit(limit);
	}
	
	public void setAddresses(AddressRange... addresses) {
		if(addresses.length != 1)
			throw new IllegalAddressException();
		if(addresses[0].getExtent() > buf.limit() - buf.position())
			throw new IllegalAddressException();
		super.setAddresses(addresses);
	}
	
	protected long getBaseAddress() {
		return getAddresses()[0].getBaseAddress();
	}
	
	protected long getEndAddress() {
		return getAddresses()[0].getEndAddress();
	}
	
	public long get(long address) {
		validateAddress(address);
		return buf.get(buf.position() + (int)(address - getBaseAddress()));
	}
	
	public void set(long address, long value) {
		validateAddress(address);;
		buf.put(buf.position() + (int)(address - getBaseAddress()), (byte) value);
	}

}

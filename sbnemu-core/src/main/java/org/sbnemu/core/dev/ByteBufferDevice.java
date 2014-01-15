package org.sbnemu.core.dev;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

/**
 * {@link Device} similar to {@link ArrayDevice} but backed by a {@link ByteBuffer}.
 * Because it is backed by a {@link ByteBuffer}, all values set on this device are
 * truncated to 1 byte.<p>
 * 
 * {@link ByteBufferDevice} supports only a single {@link AddressRange}.
 * @author robin
 *
 */
public class ByteBufferDevice extends AbstractDevice {

	/**
	 * The byte buffer
	 */
	protected ByteBuffer buf;
	
	// used only for deserialization
	@Deprecated
	public ByteBufferDevice() {
		
	}
	
	/**
	 * Wrap a {@link ByteBuffer} as the device.  The offset of memory inside
	 * the {@link ByteBuffer} is {@link ByteBuffer#position()}, and the size 
	 * is limited by {@link ByteBuffer#limit()}.
	 * @param buf
	 */
	public ByteBufferDevice(ByteBuffer buf) {
		this.buf = buf;
	}
	
	public void setAddresses(AddressRange... addresses) {
		if(addresses.length != 1) // supports only one address range
			throw new IllegalAddressException();
		if(addresses[0].getExtent() > buf.limit() - buf.position()) // ensure range isn't too large
			throw new IllegalAddressException();
		if(buf == null) { // create buffer if null, such as when deserializing
			buf = ByteBuffer.allocate((int)addresses[0].getExtent());
		}
		super.setAddresses(addresses);
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
		return buf.get(buf.position() + (int)(address - getBaseAddress()));
	}
	
	public void set(long address, long value) {
		validateAddress(address);
		buf.put(buf.position() + (int)(address - getBaseAddress()), (byte) value);
	}

	@Override
	protected String toStringData() {
		return ",size:" + (buf.limit() - buf.position());
	}
	
}

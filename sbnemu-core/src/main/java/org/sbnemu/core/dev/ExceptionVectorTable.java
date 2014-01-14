package org.sbnemu.core.dev;

import java.util.Arrays;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.CPU;
import org.sbnemu.core.ex.IllegalAddressException;
import org.sbnemu.core.ex.IllegalDataException;
import org.sbnemu.core.ex.InterruptException;

/**
 * {@link ArrayDevice} that stores a lookup table for {@link Throwable}s that
 * may be caught by the {@link CPU}.  If the {@link Throwable} is an {@link InterruptException}
 * then the unsigned value of {@link InterruptException#getInterruptId()} is subtracted from
 * the table position of {@link InterruptException} when computing the jump address 
 * @author robin
 *
 */
public class ExceptionVectorTable extends ArrayDevice {

	/**
	 * Table of known exception types
	 */
	@SuppressWarnings("unchecked")
	private static final Class<? extends Throwable>[] types = new Class[] {
		null, // position zero is any unknown exception
		NullPointerException.class,
		UnsupportedOperationException.class,
		IllegalAddressException.class,
		IllegalDataException.class,
		InterruptException.class // must be the last entry
	};
	
	public static Class<? extends Throwable>[] getTypes() {
		return Arrays.copyOf(types, types.length);
	}
	
	public ExceptionVectorTable() {
		super(types.length + 255);
		setAddresses(new AddressRange(-data.length, 0));
	}
	
	public long getAddress(Throwable thrown) {
		int offset = -1; // the offset for unknown exception types
		for(int i = 1; i < types.length; i++) { // skip the null in the types array
			if(types[i].isAssignableFrom(thrown.getClass()))
				offset = -(i + 1);
		}
		// if thrown was an InterruptException then subtract the unsigned value
		// of the interrupt id
		if(thrown instanceof InterruptException)
			offset -= 0xff & (int)(((InterruptException) thrown).getInterruptId());
		// Return the address stored in the table
		return getEndAddress() + offset;
	}

	public long get(Throwable thrown) {
		return get(getAddress(thrown));
	}
}

package org.sbnemu.core.dev;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sbnemu.core.ex.IllegalAddressException;
import org.sbnemu.core.ex.IllegalDataException;
import org.sbnemu.core.ex.InterruptException;

@RunWith(Parameterized.class)
public class ExceptionVectorTableTest {
	private ExceptionVectorTable evt = new ExceptionVectorTable();
	private Throwable thrown;
	private long expectedAddress;
	
	@Parameters(name = "{0} {1}")
	public static Iterable<Object[]> params() {
		return Arrays.asList(
				new Object[] {new RuntimeException(), -1},
				new Object[] {new NullPointerException(), -2},
				new Object[] {new UnsupportedOperationException(), -3},
				new Object[] {new IllegalAddressException(), -4},
				new Object[] {new IllegalDataException(), -5},
				new Object[] {new InterruptException((byte) 0), -6},
				new Object[] {new InterruptException((byte) 1), -7},
				new Object[] {new InterruptException((byte) -1), -6 - 255}
				);
	}
	
	public ExceptionVectorTableTest(Throwable thrown, long expectedAddress) {
		this.thrown = thrown;
		this.expectedAddress = expectedAddress;
	}
	
	@Test
	public void testGetAddress() {
		Assert.assertEquals(expectedAddress, evt.getAddress(thrown));
	}
	
	@Test
	public void testSetGet() {
		evt.set(evt.getAddress(thrown), 123);
		Assert.assertEquals(123, evt.get(expectedAddress));
	}
}

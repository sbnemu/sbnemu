package org.sbnemu.core.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

public class DefaultBusTest {
	private Bus bus;
	
	@Before
	public void before() {
		bus = new DefaultBus();
	}
	
	@Test
	public void testAddDevice() {
		Device d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		Assert.assertArrayEquals(
				new AddressRange[] {new AddressRange(0, 256)},
				bus.getAddresses());
		
		d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(1024, 1280));
		bus.addDevice(d);
		
		List<AddressRange> exp = Arrays.asList(
				new AddressRange(0, 256),
				new AddressRange(1024, 1280));
		
		List<AddressRange> act = new ArrayList<AddressRange>(Arrays.asList(bus.getAddresses()));
		Collections.sort(act);
		
		Assert.assertEquals(exp, act);
	}
	
	@Test
	public void testValidAddress() {
		Device d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		Assert.assertEquals(0, bus.get(127));
		try {
			bus.set(127, 123);
		} catch(IllegalAddressException iae) {
			Assert.fail();
		}
		Assert.assertEquals(123, bus.get(127));
	}
	
	@Test
	public void testInvalidAddress() {
		Device d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
	}
	
	@Test
	public void testOverlap() {
		Device d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(128, 384));
		try {
			bus.addDevice(d);
			Assert.fail();
		} catch(IllegalAddressException iae) {
			// expected
		}
	}
	
	@Test
	public void testNoDefaultMemory() {
		try {
			bus.get(0);
			Assert.fail();
		} catch(IllegalAddressException iae) {
			// expected
		}
	}
	
	@Test
	public void testDefaultMemory() {
		Device d = new ArrayDevice(256);
		d.setAddresses(new AddressRange(-128, 128));
		bus.setDefaultMemory(d);
		
		bus.get(0);
		bus.set(0, 123);
		Assert.assertEquals(123, bus.get(0));
	}
}

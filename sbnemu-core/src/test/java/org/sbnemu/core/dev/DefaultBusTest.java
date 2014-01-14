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
}

package org.sbnemu.core;

import org.junit.Assert;
import org.junit.Test;
import org.sbnemu.core.dev.ArrayDevice;
import org.sbnemu.core.dev.DefaultBus;

public class DefaultCPUTest {
	@Test
	public void testTickNoBranch() {
		Device d = new ArrayDevice();
		d.setAddresses(new AddressRange(0, 256));
		
		Bus bus = new DefaultBus();
		bus.addDevice(d);
		
		CPU cpu = new DefaultCPU();
		cpu.setBus(bus);
		
		cpu.tick();
		Assert.assertEquals(3, cpu.getPc());;
	}
	
	@Test
	public void testTickBranch() {
		long[] data = new long[] {3, 4, 5, 1, 2};
		Device d = new ArrayDevice(data);
		d.setAddresses(new AddressRange(0, data.length));
		
		Bus bus = new DefaultBus();
		bus.addDevice(d);;
		
		CPU cpu = new DefaultCPU();
		cpu.setBus(bus);
		
		cpu.tick();
		Assert.assertEquals(5, cpu.getPc());
		
		Assert.assertEquals(-1, bus.get(bus.get(0)));
	}
}

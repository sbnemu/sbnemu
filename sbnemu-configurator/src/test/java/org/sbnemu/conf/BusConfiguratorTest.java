package org.sbnemu.conf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;
import org.sbnemu.core.dev.ArrayDevice;
import org.sbnemu.core.dev.DefaultBus;

public class BusConfiguratorTest {
	@Test
	public void testGenerate() throws Exception {
		Bus bus = new DefaultBus();
		Device d = new ArrayDevice();
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		System.out.println(bus);
		new BusConfigurator().generate(System.out, bus);
	}
	
	@Test
	public void testConfigurate() throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Bus bus = new DefaultBus();
		Device d = new ArrayDevice();
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		String exp = bus.toString();
		
		new BusConfigurator().generate(bout, bus);
		
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		bus = new BusConfigurator().configurate(bin);
		
		String act = bus.toString();
		
		Assert.assertEquals(exp, act);
	}
}

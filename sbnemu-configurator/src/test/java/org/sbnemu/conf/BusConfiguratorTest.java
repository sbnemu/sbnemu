package org.sbnemu.conf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
		
		new BusConfigurator().generate(System.out, bus);
	}
	
	@Test
	public void testConfigurate() throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Bus bus = new DefaultBus();
		Device d = new ArrayDevice();
		d.setAddresses(new AddressRange(0, 256));
		bus.addDevice(d);
		
		new BusConfigurator().generate(bout, bus);
		
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		new BusConfigurator().configurate(bin);
	}
}

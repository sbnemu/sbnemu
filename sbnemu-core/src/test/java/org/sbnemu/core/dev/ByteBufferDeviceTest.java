package org.sbnemu.core.dev;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

public class ByteBufferDeviceTest {
	@Test
	public void testWrapped() {
		byte[] unwrapped = new byte[] {1,2,3};
		ByteBuffer buf = ByteBuffer.wrap(unwrapped);
		
		Device d = new ByteBufferDevice(buf);
		d.setAddresses(new AddressRange(0, 3));
		
		Assert.assertEquals(1, d.get(0));
		
		try {
			d.get(4);
			Assert.fail();
		} catch(IllegalAddressException iae) {
			// expected
		}
	}
	
	@Test
	public void testMapped() throws Exception {
		File f = FileUtils.toFile(ByteBufferDeviceTest.class.getResource("ByteBufferDeviceTest_mappedFile.txt"));
		FileInputStream in = new FileInputStream(f);
		ByteBuffer buf = in.getChannel().map(MapMode.READ_ONLY, 0, f.length());
		
		Device d = new ByteBufferDevice(buf);
		d.setAddresses(new AddressRange(0, 3));
		
		Assert.assertEquals((byte) '1', d.get(0));
		
		try {
			d.get(4);
			Assert.fail();
		} catch(IllegalAddressException iae) {
			// expected
		}
		
		in.close();
	}
}

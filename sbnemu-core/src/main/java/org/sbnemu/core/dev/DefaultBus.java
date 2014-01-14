package org.sbnemu.core.dev;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;
import org.sbnemu.core.Memory;
import org.sbnemu.core.ex.IllegalAddressException;

public class DefaultBus extends AbstractDevice implements Bus {

	protected NavigableMap<AddressRange, Device> devices = new TreeMap<AddressRange, Device>();
	protected Memory defaultMemory;
	
	@Override
	public void setAddresses(AddressRange... addresses) {
		throw new UnsupportedOperationException();
	}
	
	public long get(long address) {
		AddressRange q = new AddressRange(address);
		Device d = devices.get(q);
		if(d != null)
			return d.get(address);
		if(defaultMemory != null)
			return defaultMemory.get(address);
		throw new IllegalAddressException();
	}
	public void set(long address, long value) {
		AddressRange q = new AddressRange(address);
		Device d = devices.get(q);
		if(d != null)
			d.set(address, value);
		else if(defaultMemory != null)
			defaultMemory.set(address, value);
		else
			throw new IllegalAddressException();
			
	}
	public void addDevice(Device device) {
		for(AddressRange r : device.getAddresses()) {
			for(AddressRange ex : getAddresses())
				if(ex.intersects(r))
					throw new IllegalAddressException();
		}
		for(AddressRange r : device.getAddresses())
			devices.put(r, device);
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
	}
	public void removeDevice(Device device) {
		Iterator<Map.Entry<AddressRange, Device>> di = devices.entrySet().iterator();
		while(di.hasNext()) {
			Map.Entry<AddressRange, Device> e = di.next();
			if(e.getValue() == device)
				di.remove();
		}
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
	}
	public Memory getDefaultMemory() {
		return defaultMemory;
	}
	public void setDefaultMemory(Memory defaultMemory) {
		this.defaultMemory = defaultMemory;
	}
	
}

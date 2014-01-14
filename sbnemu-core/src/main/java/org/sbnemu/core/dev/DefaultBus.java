package org.sbnemu.core.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.sbnemu.core.AddressEvent;
import org.sbnemu.core.AddressListener;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;
import org.sbnemu.core.Memory;
import org.sbnemu.core.ex.IllegalAddressException;

public class DefaultBus extends AbstractDevice implements Bus, AddressListener {

	protected NavigableMap<AddressRange, Device> devices = new TreeMap<AddressRange, Device>();
	protected Memory defaultMemory;
	
	@Override
	public void setAddresses(AddressRange... addresses) {
		throw new UnsupportedOperationException();
	}
	
	public long get(long address) {
		AddressRange q = new AddressRange(address, address + 1);
		Device d = devices.get(q);
		if(d != null)
			return d.get(address);
		if(defaultMemory != null)
			return defaultMemory.get(address);
		throw new IllegalAddressException();
	}
	public void set(long address, long value) {
		AddressRange q = new AddressRange(address, address + 1);
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
		device.addAddressListener(this);
	}
	public void removeDevice(Device device) {
		Iterator<Map.Entry<AddressRange, Device>> di = devices.entrySet().iterator();
		while(di.hasNext()) {
			Map.Entry<AddressRange, Device> e = di.next();
			if(e.getValue() == device)
				di.remove();
		}
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
		device.removeAddressListener(this);
	}
	
	public Device[] getDevices() {
		return new HashSet<Device>(devices.values()).toArray(new Device[0]);
	}
	 
	public void setDevices(Device... devices) {
		this.devices.clear();
		for(Device d : devices)
			addDevice(d);
	}
	
	public Memory getDefaultMemory() {
		return defaultMemory;
	}
	public void setDefaultMemory(Memory defaultMemory) {
		this.defaultMemory = defaultMemory;
	}

	protected AddressRange[] computeAddresses() {
		Set<Device> dvs = new HashSet<Device>(devices.values());
		List<AddressRange> computed = new ArrayList<AddressRange>();
		for(Device d : dvs)
			computed.addAll(Arrays.asList(d.getAddresses()));
		Collections.sort(computed);
		return computed.toArray(new AddressRange[0]);
	}

	protected void remapDevices() {
		Set<Device> dvs = new HashSet<Device>(devices.values());
		devices.clear();
		for(Device d : dvs) {
			for(AddressRange r : d.getAddresses())
				devices.put(r, d);
		}
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
	}
	
	public void addressesChanged(AddressEvent e) {
		remapDevices();
	}
	
}

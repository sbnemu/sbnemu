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

/**
 * Default implementation of {@link Bus}
 * @author robin
 *
 */
public class DefaultBus extends AbstractDevice implements Bus, AddressListener {

	/**
	 * Map from {@link AddressRange}s to {@link Device}s contained by this
	 * bus.
	 */
	protected NavigableMap<AddressRange, Device> devices = new TreeMap<AddressRange, Device>();
	/**
	 * The default {@link Memory} to query in case no {@link AddressRange} in
	 * this bus contains a memory address.
	 */
	protected Memory defaultMemory;
	
	@Override
	public void setAddresses(AddressRange... addresses) {
		throw new UnsupportedOperationException();
	}
	
	public long get(long address) {
		// First query the devices map
		AddressRange q = new AddressRange(address, address + 1);
		Device d = devices.get(q);
		if(d != null)
			return d.get(address);
		// then check default memory
		if(defaultMemory != null)
			return defaultMemory.get(address);
		// finally, fail
		throw new IllegalAddressException();
	}
	public void set(long address, long value) {
		// first query the devices map
		AddressRange q = new AddressRange(address, address + 1);
		Device d = devices.get(q);
		if(d != null)
			d.set(address, value);
		// then check default memory
		else if(defaultMemory != null)
			defaultMemory.set(address, value);
		// finally, fail
		else
			throw new IllegalAddressException();
			
	}
	public void addDevice(Device device) {
		/* check to see that the new device doesn't overlap any
		 * addresses with existing devices
		 */
		for(AddressRange r : device.getAddresses()) {
			for(AddressRange ex : getAddresses())
				if(ex.intersects(r))
					throw new IllegalAddressException();
		}
		// Add the new device's address ranges to the devices map
		for(AddressRange r : device.getAddresses())
			devices.put(r, device);
		// update our addresses
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
		// and listen for address changes on the device
		device.addAddressListener(this);
	}
	public void removeDevice(Device device) {
		/* Remove any address ranges in the device map that point
		 * to the argument device
		 */
		Iterator<Map.Entry<AddressRange, Device>> di = devices.entrySet().iterator();
		while(di.hasNext()) {
			Map.Entry<AddressRange, Device> e = di.next();
			if(e.getValue() == device)
				di.remove();
		}
		// update our addresses
		super.setAddresses(devices.keySet().toArray(new AddressRange[0]));
		// stop listening for address changes
		device.removeAddressListener(this);
	}
	
	public Device[] getDevices() {
		return new HashSet<Device>(devices.values()).toArray(new Device[0]);
	}
	 
	public void setDevices(Device... devices) {
		// unlisten to all the devices
		for(Device d : this.devices.values())
			d.removeAddressListener(this);
		// clear the address range map
		this.devices.clear();
		// then add all the new devices
		for(Device d : devices)
			addDevice(d);
	}
	
	public Memory getDefaultMemory() {
		return defaultMemory;
	}
	public void setDefaultMemory(Memory defaultMemory) {
		this.defaultMemory = defaultMemory;
	}

	/**
	 * Remap all the devices and their address ranges
	 */
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
		// if a device has had its addresses altered, remap the devices
		remapDevices();
	}
	
	@Override
	protected String toStringData() {
		return ",devices:" + Arrays.toString(getDevices());
	}
}

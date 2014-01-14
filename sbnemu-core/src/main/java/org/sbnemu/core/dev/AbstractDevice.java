package org.sbnemu.core.dev;

import java.util.Arrays;

import javax.swing.event.EventListenerList;

import org.sbnemu.core.AddressEvent;
import org.sbnemu.core.AddressListener;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Device;
import org.sbnemu.core.ex.IllegalAddressException;

public abstract class AbstractDevice implements Device {
	
	private EventListenerList listeners = new EventListenerList();
	private AddressRange[] addresses = new AddressRange[0];
	
	public AddressRange[] getAddresses() {
		return Arrays.copyOf(addresses, addresses.length);
	}

	public void setAddresses(AddressRange... addresses) {
		AddressRange[] old = this.addresses;
		this.addresses = addresses;
		fireAddressesChanged(old, addresses);
	}

	public void addAddressListener(AddressListener l) {
		listeners.add(AddressListener.class, l);
	}

	public void removeAddressListener(AddressListener l) {
		listeners.remove(AddressListener.class, l);
	}

	protected void fireAddressesChanged(AddressRange[] oldAddresses, AddressRange[] newAddresses) {
		Object[] ll = listeners.getListenerList();
		AddressEvent e = null;
		for(int i = ll.length - 2; i >= 0; i -= 2) {
			if(ll[i] == AddressListener.class) {
				if(e == null)
					e = new AddressEvent(this, oldAddresses, newAddresses);
				((AddressListener) ll[i+1]).addressesChanged(e);
			}
		}
	}
	
	protected void validateAddress(long address) {
		for(AddressRange r : addresses) {
			if(r.contains(address))
				return;
		}
		throw new IllegalAddressException();
	}
	
}

package org.sbnemu.core;

import java.util.EventListener;

public interface AddressListener extends EventListener {
	public void addressesChanged(AddressEvent e);
}

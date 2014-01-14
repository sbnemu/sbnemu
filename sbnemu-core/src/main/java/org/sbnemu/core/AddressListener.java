package org.sbnemu.core;

import java.util.EventListener;

/**
 * Interface for objects which can listen for address changes on a {@link Device}
 * @author robin
 *
 */
public interface AddressListener extends EventListener {
	/**
	 * Called when a {@link Device}'s addresses change
	 * @param e The address event
	 */
	public void addressesChanged(AddressEvent e);
}

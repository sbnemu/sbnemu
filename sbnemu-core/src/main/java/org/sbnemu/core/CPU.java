package org.sbnemu.core;

public interface CPU {
	public void tick();
	
	public void addDevice(Device device);
	public void removeDevice(Device device);
}


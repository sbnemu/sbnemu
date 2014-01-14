package org.sbnemu.core;

public interface Device extends Memory {
	public long getBaseAddress();
	public void setBaseAddress(long address);
	
	public long getEndAddress();
	public void setEndAddress(long address);
}

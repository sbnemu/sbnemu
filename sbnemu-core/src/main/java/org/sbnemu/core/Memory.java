package org.sbnemu.core;

public interface Memory {
	public long get(long address);
	public void set(long address, long value);
}

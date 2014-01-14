package org.sbnemu.core;

import org.sbnemu.core.dev.ExceptionVectorTable;

public interface CPU {
	public void tick();
	
	public long getPc();
	public void setPc(long pc);
	
	public Bus getBus();
	public void setBus(Bus bus);
}


package org.sbnemu.core;

import org.sbnemu.core.dev.ExceptionVectorTable;

public interface CPU {
	public void tick();
	
	public Bus getBus();
	public void setBus(Bus bus);
}


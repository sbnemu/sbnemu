package org.sbnemu.core;

import org.sbnemu.core.dev.ExceptionVectorTable;

public interface CPU {
	public void tick();
	
	public Memory getMemory();
	public void setMemory(Memory memory);
}


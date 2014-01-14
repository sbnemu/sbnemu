package org.sbnemu.core;

import org.sbnemu.core.dev.DefaultBus;
import org.sbnemu.core.dev.ExceptionVectorTable;

public class DefaultCPU implements CPU {
	
	protected Bus bus = new DefaultBus();;
	protected ExceptionVectorTable exceptionVectorTable = new ExceptionVectorTable();;

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public Memory getMemory() {
		return bus;
	}

	public void setMemory(Memory memory) {
		bus = new DefaultBus();
		bus.addDevice(exceptionVectorTable);;
		bus.setDefaultMemory(memory);
	}


}

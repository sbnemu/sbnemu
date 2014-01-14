package org.sbnemu.core;

import org.sbnemu.core.dev.DefaultBus;
import org.sbnemu.core.dev.ExceptionVectorTable;

public class DefaultCPU implements CPU {
	
	protected Bus bus = new DefaultBus();;
	protected ExceptionVectorTable exceptionVectorTable = new ExceptionVectorTable();;

	protected long pc;
	
	public void tick() {
		try {
			long a = bus.get(pc);
			long b = bus.get(pc + 1);
			long c = bus.get(pc + 2);
			long s = bus.get(a) - bus.get(b);
			bus.set(a, s);
			if(s < 0)
				pc = c;
			else
				pc += 3;
		} catch(RuntimeException re) {
			pc = exceptionVectorTable.get(re);
		}
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = new DefaultBus();
		this.exceptionVectorTable = new ExceptionVectorTable();
		this.bus.addDevice(exceptionVectorTable);;
		this.bus.addDevice(bus);
	}

	public long getPc() {
		return pc;
	}

	public void setPc(long pc) {
		this.pc = pc;
	}


}

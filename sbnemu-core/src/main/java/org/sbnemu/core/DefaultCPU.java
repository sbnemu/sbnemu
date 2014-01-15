package org.sbnemu.core;

import org.sbnemu.core.dev.DefaultBus;
import org.sbnemu.core.dev.ExceptionVectorTable;
import org.sbnemu.core.ex.HaltException;

/**
 * Default implementation of {@link CPU}
 * @author robin
 *
 */
public class DefaultCPU implements CPU {
	
	/**
	 * The bus used for memory access
	 */
	protected Bus bus = new DefaultBus();;
	/**
	 * The exception vector table
	 */
	protected ExceptionVectorTable exceptionVectorTable = new ExceptionVectorTable();;

	/**
	 * The program counter
	 */
	protected long pc;
	
	public void tick() {
		try {
			// perform the instruction
			long a = bus.get(pc);
			long b = bus.get(pc + 1);
			long c = bus.get(pc + 2);
			long s = bus.get(a) - bus.get(b);
			bus.set(a, s);
			if(s < 0)
				pc = c;
			else
				pc += 3;
		} catch(HaltException halt) {
			// propagate a halt
			throw halt;
		} catch(RuntimeException re) {
			// otherwise cause an interrupt
			pc = exceptionVectorTable.get(re);
		}
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus.removeDevice(exceptionVectorTable);
		
		exceptionVectorTable = new ExceptionVectorTable();
		bus.addDevice(exceptionVectorTable);;
		this.bus = bus;
	}

	public long getPc() {
		return pc;
	}

	public void setPc(long pc) {
		this.pc = pc;
	}

	@Override
	public String toString() {
		return "{" + getClass().getSimpleName() +
				",pc:" + pc +
				",devices:" + bus + "}";
	}
}

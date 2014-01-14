package org.sbnemu.conf;

import org.sbnemu.conf.jxn.AddressRangeMixin;
import org.sbnemu.conf.jxn.BusMixin;
import org.sbnemu.conf.jxn.DeviceMixin;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SbnObjectMapper extends ObjectMapper {
	public SbnObjectMapper() {
		addMixInAnnotations(AddressRange.class, AddressRangeMixin.class);
		addMixInAnnotations(Device.class, DeviceMixin.class);
		addMixInAnnotations(Bus.class, BusMixin.class);
	}
}

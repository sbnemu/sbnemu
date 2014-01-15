package org.sbnemu.conf;

import org.sbnemu.conf.jxn.AddressRangeMixin;
import org.sbnemu.conf.jxn.BusMixin;
import org.sbnemu.conf.jxn.DeviceMixin;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.Device;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customized {@link ObjectMapper} that has pre-loaded Mixins
 * for the various {@link Device}s that might be found on a {@link Bus}
 * @author robin
 *
 */
public class SbnObjectMapper extends ObjectMapper {
	/**
	 * Create an {@link ObjectMapper} customized with mixins for
	 * the {@link Device}s that might be found on a {@link Bus}
	 */
	public SbnObjectMapper() {
		addMixInAnnotations(AddressRange.class, AddressRangeMixin.class);
		addMixInAnnotations(Device.class, DeviceMixin.class);
		addMixInAnnotations(Bus.class, BusMixin.class);
	}
}

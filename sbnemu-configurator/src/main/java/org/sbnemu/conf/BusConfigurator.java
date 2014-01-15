package org.sbnemu.conf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.sbnemu.conf.jxn.AddressRangeMixin;
import org.sbnemu.core.AddressRange;
import org.sbnemu.core.Bus;
import org.sbnemu.core.dev.DefaultBus;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to parse JSON into a {@link Bus} or to
 * generate JSON from a {@link Bus}
 * @author robin
 *
 */
public class BusConfigurator {
	/**
	 * Return a new {@link Bus} as defined by the JSON found in
	 * the {@link InputStream}
	 */
	public Bus configurate(InputStream json) throws IOException {
		ObjectMapper mapper = new SbnObjectMapper();
		return mapper.readValue(json, Bus.class);
	}
	
	/**
	 * Write JSON to the {@link OutputStream} that represents the
	 * argument {@link Bus} and can be parsed b y {@link #configurate(InputStream)}
	 */
	public void generate(OutputStream out, Bus bus) throws IOException {
		ObjectMapper mapper = new SbnObjectMapper();
		mapper.writeValue(out, bus);
	}
}

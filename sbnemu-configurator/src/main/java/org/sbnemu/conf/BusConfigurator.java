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

public class BusConfigurator {
	public Bus configurate(InputStream json) throws IOException {
		ObjectMapper mapper = new SbnObjectMapper();
		return mapper.readValue(json, Bus.class);
	}
	
	public void generate(OutputStream out, Bus bus) throws IOException {
		ObjectMapper mapper = new SbnObjectMapper();
		mapper.writeValue(out, bus);
	}
}

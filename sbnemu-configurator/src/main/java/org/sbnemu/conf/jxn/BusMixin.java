package org.sbnemu.conf.jxn;

import org.sbnemu.core.Device;
import org.sbnemu.core.Memory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="class")
@JsonIgnoreProperties("addresses")
public class BusMixin extends DeviceMixin {
	@JsonProperty("defaultMemory")
	public Memory defaultMemory;
	
	@JsonProperty("devices")
	public Device[] devices;
}

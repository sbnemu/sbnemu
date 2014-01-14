package org.sbnemu.conf.jxn;

import org.sbnemu.core.AddressRange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.MINIMAL_CLASS, include=As.PROPERTY, property="class")
public class DeviceMixin {
	@JsonProperty("addresses")
	public AddressRange[] addresses;
}

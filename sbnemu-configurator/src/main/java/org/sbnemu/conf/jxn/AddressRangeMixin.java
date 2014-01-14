package org.sbnemu.conf.jxn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties("extent")
public class AddressRangeMixin {
	@JsonProperty("base")
	public long baseAddress;
	
	@JsonProperty("end")
	public long endAddress;
	
}

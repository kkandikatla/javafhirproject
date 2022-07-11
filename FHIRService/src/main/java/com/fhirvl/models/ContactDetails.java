package com.fhirvl.models;

import java.io.Serializable;

public class ContactDetails  implements Serializable{
	

	private static final long serialVersionUID = 195141790916650231L;
	
	
	private String system;
	private String use;
	private String value;
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
}

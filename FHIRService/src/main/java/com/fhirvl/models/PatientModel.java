package com.fhirvl.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


//POJO/Model for Input Json String
public class PatientModel{
	
	private static final long serialVersionUID = 368680618767963429L;

	private boolean active;
	
	private String name;

	private String family;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;
	
	private String gender;
	
	private List<ContactDetails> contactDetails;
	
	private List<AddressDetails> addressDetails;
	

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Date getDob() {
		return dob;
	}

	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	
	

}

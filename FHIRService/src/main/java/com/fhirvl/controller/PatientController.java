package com.fhirvl.controller;

import org.hl7.fhir.dstu2.model.ValueSet.ValueSetCodeSystemComponent;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhirvl.models.AddressDetails;
import com.fhirvl.models.ContactDetails;
import com.fhirvl.models.PatientModel;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;


@RestController
@RequestMapping("/patient")
public class PatientController {


	@PostMapping("/execute")
	public String executeJsonToFhir(@RequestBody String jsonInput) throws JsonMappingException, JsonProcessingException  {
		
	FhirContext fhirContext = FhirContext.forR4();

	//Deserializing input json string & mapping to PatientModel class
	ObjectMapper mapper =new ObjectMapper();
	PatientModel patientModel = mapper.readValue(jsonInput, PatientModel.class);

	//Creating an instance of R4 Patient class
	Patient patient = new Patient();

	//Setting values in R4 Patient object from PatientModel class (input jsonString
	patient.setActive(patientModel.isActive());
	patient.addName().setFamily(patientModel.getName()).addGiven(patientModel.getFamily());
	patient.setBirthDate(patientModel.getDob());
	patient.setGender(AdministrativeGender.valueOf(patientModel.getGender()));
	
	// Setting Telecom (Contact Details)		
		if(patientModel.getContactDetails().size() >0)
		{
			for(ContactDetails cd : patientModel.getContactDetails())
			{
				 patient.addTelecom()
		         .setSystem(ContactPointSystem.valueOf(cd.getSystem().toUpperCase()))
		         .setUse(ContactPointUse.valueOf(cd.getUse().toUpperCase()))
		         .setValue(cd.getValue());
			}
		}
	
	//Setting Address details	
		if(patientModel.getAddressDetails().size() > 0)
		{
			for(AddressDetails ad : patientModel.getAddressDetails())
			{
				patient.addAddress()
				.setUse(AddressUse.valueOf("HOME"))
				.setText(ad.getStreet1())
				//.setLine(ad.setLine())
				.setCity(ad.getCity())
				.setState(ad.getState())
				.setPostalCode(ad.getZipCode());
				
				
			}
		}
		
		
		
		// Instantiate a new parser for FHIR 
		IParser parser = fhirContext.newJsonParser();

		//Parse the R4 Patient class object to FHIR string
		String str = parser.encodeResourceToString(patient);
		
		System.out.println(str);
	
		return str;

	}
}

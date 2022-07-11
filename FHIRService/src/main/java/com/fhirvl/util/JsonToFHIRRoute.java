package com.fhirvl.util;

import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;


@Component
public class JsonToFHIRRoute extends RouteBuilder {

	

    @Override
    public void configure() throws Exception {
        
        from("direct:jsonInput")
       .unmarshal(new JacksonDataFormat(Patient.class))
        .to("mock:marshalledObject");
        //.to("fhir://transaction/withBundle?inBody=bundle&serverUrl={{url}}&fhirVersion={{version}}")
        
     
        
    }
}

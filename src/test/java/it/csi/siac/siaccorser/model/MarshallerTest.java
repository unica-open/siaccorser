/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser.model;

import static org.junit.Assert.fail;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

/**
 * Testa il marshalling in XML degli oggetti del dominio.
 * 
 * @see http://cmaki.blogspot.it/2007/09/annotated-jaxb-classes.html
 * 
 * @author alagna
 * @version $Id: $
 */
public class MarshallerTest {

	@Before
	public void setUp() throws Exception {
		// Nessun setup
	}

	@Test
	public void test() {
		try {
			AzioneRichiesta ar = new AzioneRichiesta();
			
			// Get a JAXB Context for the object we created above
			JAXBContext context = JAXBContext.newInstance(ar.getClass());
			// To convert ex to XML, I need a JAXB Marshaller
			Marshaller marshaller = context.createMarshaller();
			// Make the output pretty
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			// marshall the object to XML
			marshaller.marshal(ar, sw);
			// print it out for this example
			System.out.println(sw.toString());			
			
		} catch (Exception e){
			fail("errore: " + e.getMessage());
		}
	}

}

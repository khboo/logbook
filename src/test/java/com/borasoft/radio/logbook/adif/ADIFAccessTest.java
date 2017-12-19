package com.borasoft.radio.logbook.adif;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.junit.Test;

public class ADIFAccessTest {
	@Test
	public void testADIFRead() throws URISyntaxException, IOException {
		FileInputStream stream = new FileInputStream(new java.io.File(ADIFAccessTest.class.getClassLoader().getResource("ve3he_2017-12-09.ADI").toURI()));
		InputStreamReader reader = new InputStreamReader(stream);
		ADIFReader adifReader = new ADIFReader(reader);
		File adif= adifReader.readADIF_File();
		reader.close();
		System.out.println(adif.header.freetext.trim());
		assertEquals(adif.header.freetext.trim(),"header <adif_ver:4>1.00 From VE6YP log programme.");
		assertEquals(adif.header.adif_ver,"1.00");
		assertEquals(adif.records.size(),9);
	}
	/*
	@Test
	public void testADIFWrite() {
		FileOutputStream ostream = new FileOutputStream(new File(ADIFAccessTest.class.getClassLoader().getResource("ve3he_2017-12-09_copy.ADI").toURI()));
		OutputStreamWriter writer = new OutputStreamWriter(ostream);
		ADIFWriter adifWriter = new ADIFWriter(writer,adif);
		adifWriter.writeADIFStream();
		writer.close();		
	}
	*/
}
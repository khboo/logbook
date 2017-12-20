package com.borasoft.radio.logbook.adif;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

import org.junit.Test;

public class ADIFAccessTest {
	@Test
	public void testADIFReadWrite() throws URISyntaxException, IOException {
		FileInputStream stream = new FileInputStream(new java.io.File(getClass().getClassLoader().getResource("ve3he_2017-12-09.adi").toURI()));
		InputStreamReader reader = new InputStreamReader(stream);
		ADIFReader adifReader = new ADIFReader(reader);
		File adif= adifReader.readADIF_File();
		reader.close();
		assertEquals(adif.header.freetext.trim(),"header <adif_ver:4>1.00 From VE6YP log programme.");
		assertEquals(adif.header.adif_ver,"1.00");
		assertEquals(adif.records.size(),9);
		
		FileOutputStream ostream = new FileOutputStream(new java.io.File("ve3he_2017-12-09_copy.adi"));
		OutputStreamWriter writer = new OutputStreamWriter(ostream);
		ADIFWriter adifWriter = new ADIFWriter(writer,adif);
		adifWriter.writeADIF_File();
		writer.close();		
	}
}

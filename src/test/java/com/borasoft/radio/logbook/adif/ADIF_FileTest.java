package com.borasoft.radio.logbook.adif;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ADIF_FileTest {
	final String expectedADIF=
			"Hello header<adif_ver:3>1.0<programid:4>1234<programversion:3>1.0<eoh>\n"
			+"<call:6>va3pen<qso_date:8>20171130<time_on:4>1300<band:3>20m<mode:3>SSB<eor>\n"
			+"<call:5>ve3he<qso_date:8>20171215<time_on:4>1300<band:3>40m<mode:3>SSB<eor>\n";
	File file=new File();
	@Before
	public void setup() {
		Header header=new Header();
		header.freetext="Hello header";
		header.adif_ver="1.0";
		header.programid="1234";
		header.programversion="1.0";
		file.header=header;
		Record record=new Record("va3pen","20171130","1300","20m","SSB");
		file.addRecord(record);
		record=new Record("ve3he","20171215","1300","40m","SSB");
		file.addRecord(record);
	}
	@Test
	public void testADIFOutput() {
		System.out.println(file.toADIFString());
		assertEquals("ADIF output does not match the expected value.",expectedADIF,file.toADIFString());
	}
}

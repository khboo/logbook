package com.borasoft.radio.logbook.adif;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class HeaderTest {
	Header header;
	final String expectedJSON="{\"freetext\":\"Hello header\",\"programversion\":\"1.0\",\"adif_ver\":\"1.0\",\"programid\":\"1234\"}";
	final String expectedADIF="Hello header<adif_ver:3>1.0<programid:4>1234<programversion:3>1.0<eoh>";
	@Before
	public void setup() {
		header=new Header();
		header.freetext="Hello header";
		header.adif_ver="1.0";
		header.programid="1234";
		header.programversion="1.0";
	}

	@Test
	public void testJSONOutput() {
		try {
			//System.out.println(header.toJSONObject().toString());
			assertEquals("JSON output does not match the expected value.",expectedJSON,header.toJSONObject().toString());
		} catch (JSONException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testADIFOutput() {
		//System.out.println(header.toADIFString());
		assertEquals("ADIF output does not match the expected value.",expectedADIF,header.toADIFString());
	}
}

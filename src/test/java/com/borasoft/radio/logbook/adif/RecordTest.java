package com.borasoft.radio.logbook.adif;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class RecordTest {
	Record record;
	final String expectedJSON="{\"call\":\"va3pen\",\"mode\":\"SSB\",\"qso_date\":\"20171130\",\"band\":\"20m\",\"time_on\":\"1300\"}";
	final String expectedADIF="<call:6>va3pen<qso_date:8>20171130<time_on:4>1300<band:3>20m<mode:3>SSB<eor>";
	@Before
	public void setup() {
		record=new Record("va3pen","20171130","1300","20m","SSB");
	}

	@Test
	public void testJSONOutput() {
		try {
			assertEquals("JSON output does not match the expected value.",expectedJSON,record.toJSONObject().toString());
		} catch (JSONException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testADIFOutput() {
		assertEquals("ADIF output does not match the expected value.",expectedADIF,record.toADIFString());
	}
}

package com.borasoft.radio.logbook.adif;

import org.json.JSONException;
import org.json.JSONObject;

class Header {
	String freetext=""; // header start. should not be starting with '<'. header ends with <eoh>.
	String adif_ver="1.0";
	String programid="BORASOFT_LOGBOOK";
	String programversion="1.0";
	JSONObject toJSONObject() throws JSONException {
		JSONObject record= new JSONObject();
		addIfNotNull(record,"freetext",freetext);
		addIfNotNull(record,"adif_ver",adif_ver);
		addIfNotNull(record,"programid",programid);
		addIfNotNull(record,"programversion",programversion);	
		return record;
	}
	void addIfNotNull(JSONObject record,String field,String value) throws JSONException {
		if(value!=null) {
			record.put(field,value);
		}
	}
	String toADIFString() {
		StringBuilder record=new StringBuilder();
		record.append(freetext);
		addIfNotNull(record,"adif_ver",adif_ver);
		addIfNotNull(record,"programid",programid);
		addIfNotNull(record,"programversion",programversion);	
		record.append("<eoh>");
		return record.toString();
	}
	void addIfNotNull(StringBuilder record,String field,String value) {
		if(value!=null) {
			record.append("<"+field+":"+value.length()+">"+value);
		}
	}	
}

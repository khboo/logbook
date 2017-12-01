package com.borasoft.radio.logbook.adif;

import org.json.JSONException;
import org.json.JSONObject;

class Record {
	/* mandatory */
	String call;
	String qso_date;
	String time_on;
	String band;
	String mode;
	
	/* optional */
	String freq;
	String name;
	String rst_rcvd;
	String rst_sent;
	String qsl_rcvd;
	String qsl_sent;
	String time_off;
	String comment;
	String qth;
	String tx_pwr;
	String ituz; // ITU zone
	String cqz; // CQ zone
	String dxcc;
	
	@SuppressWarnings("unused")
	private Record() {
	}
	Record(String call,String qso_date,String time_on,String band,String mode) {
		this.call=call;
		this.qso_date=qso_date;
		this.time_on=time_on;
		this.band=band;
		this.mode=mode;
	}
	JSONObject toJSONObject() throws JSONException {
		JSONObject record= new JSONObject();
		addIfNotNull(record,"call",call);
		addIfNotNull(record,"qso_date",qso_date);
		addIfNotNull(record,"time_on",time_on);
		addIfNotNull(record,"band",band);
		addIfNotNull(record,"mode",mode);
		addIfNotNull(record,"freq",freq);
		addIfNotNull(record,"name",name);
		addIfNotNull(record,"rst_rcvd",rst_rcvd);
		addIfNotNull(record,"rst_sent",rst_sent);
		addIfNotNull(record,"qsl_rcvd",qsl_rcvd);
		addIfNotNull(record,"qsl_sent",qsl_sent);
		addIfNotNull(record,"time_off",time_off);
		addIfNotNull(record,"comment",comment);
		addIfNotNull(record,"qth",qth);
		addIfNotNull(record,"tx_pwr",tx_pwr);
		addIfNotNull(record,"ituz",ituz);
		addIfNotNull(record,"cqz",cqz);
		addIfNotNull(record,"dxcc",dxcc);		
		return record;
	}
	void addIfNotNull(JSONObject record,String field,String value) throws JSONException {
		if(value!=null) {
			record.put(field,value);
		}
	}
	String toADIFString() {
		StringBuilder record=new StringBuilder();
		addIfNotNull(record,"call",call);
		addIfNotNull(record,"qso_date",qso_date);
		addIfNotNull(record,"time_on",time_on);
		addIfNotNull(record,"band",band);
		addIfNotNull(record,"mode",mode);
		addIfNotNull(record,"freq",freq);
		addIfNotNull(record,"name",name);
		addIfNotNull(record,"rst_rcvd",rst_rcvd);
		addIfNotNull(record,"rst_sent",rst_sent);
		addIfNotNull(record,"qsl_rcvd",qsl_rcvd);
		addIfNotNull(record,"qsl_sent",qsl_sent);
		addIfNotNull(record,"time_off",time_off);
		addIfNotNull(record,"comment",comment);
		addIfNotNull(record,"qth",qth);
		addIfNotNull(record,"tx_pwr",tx_pwr);
		addIfNotNull(record,"ituz",ituz);
		addIfNotNull(record,"cqz",cqz);
		addIfNotNull(record,"dxcc",dxcc);		
		record.append("<eor>");
		return record.toString();
	}
	void addIfNotNull(StringBuilder record,String field,String value) {
		if(value!=null) {
			record.append("<"+field+":"+value.length()+">"+value);
		}
	}
}

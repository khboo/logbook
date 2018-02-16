package com.borasoft.radio.logbook.adif;

import org.json.JSONException;
import org.json.JSONObject;

public class Record {
	/* mandatory */
	String call;
	String qso_date;
	String time_on;
	String band;
	String mode;
	
	/* optional */
	public String freq;
	public String name;
	public String rst_rcvd;
	public String rst_sent;
	String qsl_rcvd;
	String qsl_sent;
	public String time_off;
	public String comment;
	public String qth;
	String tx_pwr;
	String ituz; // ITU zone
	String cqz; // CQ zone
	String dxcc;
	
	private Record() {
	}
	public Record(String call,String qso_date,String time_on,String band,String mode) {
		this.call=call;
		this.qso_date=qso_date;
		this.time_on=time_on;
		this.band=band;
		this.mode=mode;
	}
	static public Record fromJSONObject(JSONObject jobj) throws JSONException{
		Record record=new Record();
		/* mandatory */
		record.call=jobj.getString("call");
		record.qso_date=jobj.getString("qso_date");
		record.time_on=jobj.getString("time_on");
		record.band=jobj.getString("band");
		record.mode=jobj.getString("mode");	
		
		/* optional */
		record.freq=jobj.optString("freq",null);
		record.name=jobj.optString("name",null);
		record.rst_rcvd=jobj.optString("rst_rcvd",null);
		record.rst_sent=jobj.optString("rst_sent",null);
		record.qsl_rcvd=jobj.optString("qsl_rcvd",null);
		record.qsl_sent=jobj.optString("qsl_sent",null);
		record.time_off=jobj.optString("time_off",null);
		record.comment=jobj.optString("comment",null);
		record.qth=jobj.optString("qth",null);
		record.tx_pwr=jobj.optString("tx_pwr",null);
		record.ituz=jobj.optString("ituz",null); // ITU zone
		record.cqz=jobj.optString("cqz",null); // CQ zone
		record.dxcc=jobj.optString("dxcc",null);		
		
		return record;
	}
	public JSONObject toJSONObject() throws JSONException {
		JSONObject record= new JSONObject();
		/* mandatory */
		record.put("call",call);
		record.put("qso_date",qso_date);
		record.put("time_on",time_on);
		record.put("band",band);
		record.put("mode",mode);
		
		/* optional */
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
	public String toADIFString() {
		StringBuilder record=new StringBuilder();
		/* mandatory */
		addIfNotNull(record,"call",call);
		addIfNotNull(record,"qso_date",qso_date);
		addIfNotNull(record,"time_on",time_on);
		addIfNotNull(record,"band",band);
		addIfNotNull(record,"mode",mode);
		
		/* optional */
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

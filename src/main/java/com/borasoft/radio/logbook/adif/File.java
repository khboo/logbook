package com.borasoft.radio.logbook.adif;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class File {
	Header header;
	List<Record> records;
	public String toADIFString() {
		StringBuilder builder=new StringBuilder();
		if(header!=null) {
			builder.append(header.toADIFString()+"\n");
		}
		Iterator<Record> iter=records.iterator();
		Record record=null;
		while(iter.hasNext()) {
			record=iter.next();
			builder.append(record.toADIFString()+"\n");
		}
		return builder.toString();
	}
	public JSONObject toJSONObject() throws JSONException {
		JSONObject file=new JSONObject();
		if(header!=null) {
			file.put("header",header.toJSONObject());
		}
		Iterator<Record> iter=records.iterator();
		Record record=null;
		JSONArray jrecords=new JSONArray();
		while(iter.hasNext()) {
			record=iter.next();
			jrecords.put(record.toJSONObject());
		}	
		file.put("records", jrecords);
		return file;
	}
	public void addRecord(Record record) {
		if(records==null) {
			records=new ArrayList<Record>();
		}
		records.add(record);
	}
}

package com.borasoft.radio.logbook.adif;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public void addRecord(Record record) {
		if(records==null) {
			records=new ArrayList<Record>();
		}
		records.add(record);
	}
}

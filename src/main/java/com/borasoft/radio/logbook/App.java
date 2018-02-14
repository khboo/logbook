package com.borasoft.radio.logbook;

import java.io.IOException;
import com.borasoft.radio.logbook.adif.Record;
import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class App {
    public static void main( String[] args ) throws IOException {
    	// Type 'exit' to terminate the shell.
        ShellFactory.createConsoleShell("","",new App()).commandLoop();
        System.out.println("Bye.");
    }
    
    @Command(description="Add a new QSO.",name="add")
    public boolean add() throws IOException {
    	/*
    	String call;
    	String qso_date;
    	String time_on;
    	String band;
    	String mode;
    	
    	String freq;
    	String name;
    	String rst_rcvd;
    	String rst_sent;
    	String qsl_rcvd;
    	String qsl_sent;
    	String time_off;
    	String comment;
    	String qth;
    	*/
    	String call;
    	String qso_date;
    	String time_on;
    	String band;
    	String mode;
    	
    	System.out.print("CALLSIGN: ");
    	byte[] b=new byte[80];
    	System.in.read(b);
    	StringBuilder sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append(Character.toUpperCase((char)b[i]));
    	}
    	call=sb.toString();
   	
    	System.out.print("QSO DATE(YYYYMMDD): ");
    	b=new byte[9];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	qso_date=sb.toString();

    	System.out.print("TIME ON(HHMM): ");
    	b=new byte[5];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	time_on=sb.toString();
   
    	System.out.print("BAND(160m/80m/60m/40m/30m/20m/17m/15m/12m/10m/6m): ");
    	b=new byte[5];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append(Character.toLowerCase((char)b[i]));
    	}
    	band=sb.toString();
    	
    	System.out.print("MODE(CW/SSB/AM/FM/DIGITAL): ");
    	b=new byte[8];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append(Character.toUpperCase((char)b[i]));
    	}
    	mode=sb.toString();
    	
    	Record record=new Record(call,qso_date,time_on,band,mode);    	
    	System.out.print("FREQUENCY(mHz): ");
    	b=new byte[11];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	String freq=sb.toString();
    	if(freq.isEmpty()) {
    		switch(band) {
    			case "160m": freq="1.6 mHz"; break;
    			case "80m": freq="3.5 mHz"; break;
    			case "60m": freq="5 mHz"; break;
    			case "40m": freq="7 mHz"; break;
    			case "30m": freq="10 mHz"; break;
    			case "20m": freq="14 mHz"; break;
    			case "17m": freq="18 mHz"; break;
    			case "15m": freq="21 mHz"; break;
    			case "12m": freq="24 mHz"; break;
    			case "10m": freq="28 mHz"; break;
    			case "6m": freq="50 mHz"; break;
    			default: break;
    		}
    	}
    	record.freq=freq;
    	
    	System.out.print("NAME: ");
    	b=new byte[80];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	record.name=sb.toString();
    	
    	System.out.print("QTH: ");
    	b=new byte[80];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	record.qth=sb.toString();
    	
    	System.out.print("RST RECEIVED: ");
    	b=new byte[4];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	record.rst_rcvd=sb.toString();
    	
    	System.out.print("RST SENT: ");
    	b=new byte[4];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	record.rst_sent=sb.toString();
    	
    	System.out.print("COMMENET: ");
    	b=new byte[255];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;b[i]!='\n';i++) {
    		if(i<b.length) {
    			sb.append((char)b[i]);
    		} else {
    			break;
    		}
    	}
    	record.comment=sb.toString();

    	System.out.print("TIME OFF(HHMM): ");
    	b=new byte[5];
    	System.in.read(b);
    	sb=new StringBuilder();
    	for(int i=0;i<b.length && b[i]!='\n';i++) {
    		sb.append((char)b[i]);
    	}
    	record.time_off=sb.toString();
    	
    	System.out.println(record.toADIFString());   	
    	return true;
    }    
}

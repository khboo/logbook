package com.borasoft.radio.logbook.adif;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class ADIFQSODateFilter {
	private int totalRecords = 0;
	private int filteredRecords = 0;

	/**
	 * @param args
	 * arg[0] - Input ADIF filename
	 * arg[1] - Output ADIF filename
	 * arg[2] - Starting QSO date (yyyymmdd)
	 * arg[3] - Ending QSO date (yyyymmdd)
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("\nADIF QSO date filter, 2011, BoraSoft(c)\n");

		if (args.length != 4) {
			System.out.println("Syntax Error: \n");
			System.out.println("java ADIFQSOFilter [input file][output file][starting QSO date(yyyymmdd)][ending QSO date]\n");
			System.exit(-1);
		}
				
		System.out.println("Processing QSO records between [" + args[2] + "] and [" + args[3] + "] >>>");
		
		String inputFilename = args[0];
		String outputFilename = args[1];
		String startingDate = args[2];
		String endingDate = args[3];

		ADIFQSODateFilter filter = new ADIFQSODateFilter();
		filter.processADIF(inputFilename, outputFilename, startingDate, endingDate);
		
		System.out.println("Procesing completed successfully.");
	}
	
	public void processADIF(String inputFilename, String outputFilename, String startingDate, String endingDate) 
			throws FileNotFoundException, IOException {
		FileInputStream stream = new FileInputStream(inputFilename);
		InputStreamReader reader = new InputStreamReader(stream);
		ADIFReader adifReader = new ADIFReader(reader);
		File adif = adifReader.readADIF_File();
		reader.close();
		
		FileOutputStream ostream = new FileOutputStream(outputFilename);
		OutputStreamWriter writer = new OutputStreamWriter(ostream);
		
		List<Record> records = adif.records;
		File newADIF = new File();
		Record record;
		Record prevRecord=null; // To remove duplicated records
		String qsoDate;
		Header header=new Header();
		header.freetext="ADIF QSO date filter, BoraSoft(c)";
		header.adif_ver="1.00";
		newADIF.header=header;
		if (records!=null) {
			totalRecords = records.size();
			for (int i=0; i<totalRecords; i++) {
				record = records.get(i);
				if(!isDup(prevRecord,record)) {
					qsoDate = record.qso_date;
					if (Integer.decode(qsoDate)>=Integer.decode(startingDate) && Integer.decode(qsoDate)<=Integer.decode(endingDate)) {
						newADIF.addRecord(record);
						filteredRecords++;
					}
				} else {
					System.out.println("Removed a duplicated entry:");
					System.out.print("\t" + record.call);
					System.out.print(" on " + record.qso_date +", ");
					System.out.println(record.time_on +" UTC.");
				}
				prevRecord = record;
			}
			ADIFWriter adifWriter = new ADIFWriter(writer,newADIF);
			adifWriter.writeADIF_File();;
			writer.close();	
		}
		
		System.out.println("Total number of QSO records processed: " + totalRecords + ".");
		System.out.println("Total number of QSO records written to the new file: " + filteredRecords + ".");
	}

	public int getTotalRecords() {
		return totalRecords;
	}
	
	public int getFilteredRecords() {
		return filteredRecords;
	}
	
	private boolean isDup(Record prev, Record current) {
		if(prev==null) {
			return false; // first record
		}
		if(prev.call.equalsIgnoreCase(current.call)
		   && prev.mode.equalsIgnoreCase(current.mode)
		   && prev.qso_date.equalsIgnoreCase(current.qso_date)
		   && prev.time_on.equalsIgnoreCase(current.time_on)) {
			return true;
		} else {
			return false;
		}
	}

}

package com.borasoft.radio.logbook.adif;

@Deprecated
class ADIFHeader {
	private String text;
	private String file;
	private String adifVer;
	private String programID;
	private String programVersion;
	
	public String getText() {
		return text;
	}
	public void setText(String s) {
		text=s;
	}
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getADIFVer() {
		return adifVer;
	}
	
	public void setADIFVer(String adifVer) {
		this.adifVer = adifVer;
	}
	
	public String getProgramID() {
		return programID;
	}
	
	public void setProgramID(String programID) {
		this.programID = programID;
	}
	
	public String getProgramVersion() {
		return programVersion;
	}
	
	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}
	
}

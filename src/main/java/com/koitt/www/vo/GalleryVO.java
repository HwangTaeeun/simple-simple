package com.koitt.www.vo;

import java.sql.Date;

public class GalleryVO {
	private int pno;
	private int mno;
	private String gBody;
	private String oriName;
	private String saveName;
	private long len;
	private String dir;
	private Date pDate;
	private String sDate;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getgBody() {
		return gBody;
	}
	public void setgBody(String gBody) {
		this.gBody = gBody;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getDir() {
		return dir;
	}
	
	public void setDir(String dir) {
	this.dir = "/resources/upload";	
	//this.dir = dir;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	
	
}

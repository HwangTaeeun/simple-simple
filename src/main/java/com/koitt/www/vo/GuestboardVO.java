package com.koitt.www.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class GuestboardVO {
	
	private int gsno;
	private int mno;
	private String gsbody;
	private Date gsDate;
	private String sGsDate;
	private Time gsTime;
	private String sGsTime;
	
	
	public int getGsno() {
		return gsno;
	}
	public void setGsno(int gsno) {
		this.gsno = gsno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getGsbody() {
		return gsbody;
	}
	public void setGsbody(String gsbody) {
		this.gsbody = gsbody;
	}
	public Date getGsDate() {
		return gsDate;
	}
	public void setGsDate(Date gsDate) {
		this.gsDate = gsDate;
		setsGsDate();
	}
	public String getsGsDate() {
		return sGsDate;
	}
	public void setsGsDate(String sGsDate) {
		this.sGsDate = sGsDate;
	}
	public void setsGsDate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		this.sGsDate = form.format(gsDate);
	}
	
	public Time getGsTime() {
		return gsTime;
	}
	public void setGsTime(Time gsTime) {
		this.gsTime = gsTime;
		setsGsTime();
	}
	public String getsGsTime() {
		return sGsTime;
	}
	public void setsGsTime(String sGsTime) {
		this.sGsTime = sGsTime;
	}
	public void setsGsTime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		this.sGsTime = form.format(gsTime);
	}
	
	
	
}

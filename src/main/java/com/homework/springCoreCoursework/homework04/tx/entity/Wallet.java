package com.homework.springCoreCoursework.homework04.tx.entity;

public class Wallet {
	private int wid;
	private String wname;
	private int money;
	
	public Wallet() {}
	
	public Wallet(int wid, String wname, int money) {
		super();
		this.wid = wid;
		this.wname = wname;
		this.money = money;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}	
}
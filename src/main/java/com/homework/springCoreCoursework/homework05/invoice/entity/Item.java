package com.homework.springCoreCoursework.homework05.invoice.entity;

public class Item {
	private Integer id;
	private Integer amount;
	private Integer ipid;
	private Integer invid;
	
	public Item() {}

	public Item(Integer id, Integer amount, Integer ipid, Integer invid) {
		super();
		this.id = id;
		this.amount = amount;
		this.ipid = ipid;
		this.invid = invid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getIpid() {
		return ipid;
	}

	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}

	public Integer getInvid() {
		return invid;
	}

	public void setInvid(Integer invid) {
		this.invid = invid;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", amount=" + amount + ", ipid=" + ipid + ", invid=" + invid + "]";
	}	
}
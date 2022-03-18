package com.homework.springCoreCoursework.homework04.tx.entity;

public class CustomerOrders {
	private int o_id;
	private int u_id;
	private int p_id;
	private String receiverInfo;
	private String pName;
	private double totalPrice;
	private int quantity;
	private String orderTime;
	
	public CustomerOrders() {}

	public CustomerOrders(int o_id, int u_id, int p_id, String receiverInfo, String pName, double totalPrice,
			int quantity, String orderTime) {
		super();
		this.o_id = o_id;
		this.u_id = u_id;
		this.p_id = p_id;
		this.receiverInfo = receiverInfo;
		this.pName = pName;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.orderTime = orderTime;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "CustomerOrders [o_id=" + o_id + ", u_id=" + u_id + ", p_id=" + p_id + ", receiverInfo=" + receiverInfo
				+ ", pName=" + pName + ", totalPrice=" + totalPrice + ", quantity=" + quantity + ", orderTime="
				+ orderTime + "]";
	}	
}
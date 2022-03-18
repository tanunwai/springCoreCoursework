package com.homework.springCoreCoursework.homework04.tx.exception;

public class InsufficientOrders extends Exception{

	private static final long serialVersionUID = 5920022618777989440L;

	public InsufficientOrders(String message) {
		super(message);
	}
}
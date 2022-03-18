package com.homework.springCoreCoursework.homework04.tx.exception;

public class InsufficientQuantity extends Exception{

	private static final long serialVersionUID = 1004134626594338299L;

	public InsufficientQuantity(String message) {
		super(message);
	}
}
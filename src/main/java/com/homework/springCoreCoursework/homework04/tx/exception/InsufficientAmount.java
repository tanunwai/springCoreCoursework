package com.homework.springCoreCoursework.homework04.tx.exception;

public class InsufficientAmount extends Exception{

	private static final long serialVersionUID = -8175748471590771517L;

	public InsufficientAmount(String message) {
		super(message);
	}
}
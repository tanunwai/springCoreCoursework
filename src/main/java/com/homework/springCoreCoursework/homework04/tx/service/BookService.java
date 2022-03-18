package com.homework.springCoreCoursework.homework04.tx.service;

import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientAmount;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientOrders;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientQuantity;

public interface BookService {
	void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity, InsufficientOrders;
	void buyMany(Integer wid, Integer... bids)throws InsufficientAmount, InsufficientQuantity, InsufficientOrders;
	String getNameDetail(Integer wid, Integer bid);
}

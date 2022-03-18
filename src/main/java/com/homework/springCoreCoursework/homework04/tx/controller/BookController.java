package com.homework.springCoreCoursework.homework04.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientAmount;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientOrders;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientQuantity;
import com.homework.springCoreCoursework.homework04.tx.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Integer bid) {
		try {
			bookService.buyOne(wid, bid);
			System.out.println("單筆 buyBook OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足 :" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足 :" + e);
		}catch(InsufficientOrders e) {
			System.err.println("訂單記錄有誤 :"+ e);
		}
	}

	public void buyBooks(Integer wid, Integer... bids) {
		try {
			bookService.buyMany(wid, bids);
			System.out.println("多筆 buyBooks OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足 :" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足 :" + e);
		}catch(InsufficientOrders e) {
			System.err.println("訂單記錄有誤 :"+ e);
		}
	}
	
	public void getNameDetail(Integer wid, Integer bid) {
		String dName=bookService.getNameDetail(wid, bid);
		System.out.println("名稱資訊 :"+dName);
	}
}

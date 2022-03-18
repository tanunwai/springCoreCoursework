package com.homework.springCoreCoursework.homework04.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.springCoreCoursework.homework04.tx.controller.BookController;

public class TestBook {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookController bookController=ctx.getBean(BookController.class);
		//System.out.println(bookController);
		//Case1:
		//Integer wid=2;
		//Integer bid=1;
		//bookController.buyBook(wid, bid);
		
		//Case2:
		Integer wid=2;
		bookController.buyBooks(wid, 1,1,2,2,2);
		
		//Test1:
		//Integer wid=1,bid=2;
		//bookController.getNameDetail(wid, bid);
		((ClassPathXmlApplicationContext) ctx).close();
	}	
}
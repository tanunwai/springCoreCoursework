package com.homework.springCoreCoursework.homework04.tx.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.homework.springCoreCoursework.homework04.tx.dao.BookDao;
import com.homework.springCoreCoursework.homework04.tx.dao.OrdersDao;
import com.homework.springCoreCoursework.homework04.tx.entity.CustomerOrders;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientAmount;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientOrders;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientQuantity;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private OrdersDao ordersDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {InsufficientAmount.class, InsufficientOrders.class, InsufficientQuantity.class}, noRollbackFor = {ArithmeticException.class})
	@Override
	public void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity, InsufficientOrders {
		bookDao.updateStock(bid, 1);
		Integer price = bookDao.getPrice(bid);
		bookDao.updateWallet(wid, price);
		// Log...
		String bName = bookDao.getBooksDetail().stream().filter(e -> Integer.valueOf(e.getBid()).equals(bid))
				.findFirst().get().getBname();
		String wName = bookDao.getWalletDetail().stream().filter(e -> Integer.valueOf(e.getWid()).equals(wid))
				.findFirst().get().getWname();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		CustomerOrders customerOrders = new CustomerOrders();
		customerOrders.setP_id(bid);
		customerOrders.setU_id(wid);
		customerOrders.setReceiverInfo(wName);
		customerOrders.setpName(bName);
		customerOrders.setTotalPrice(price);
		customerOrders.setQuantity(1);
		customerOrders.setOrderTime(dateTime);
		
		ordersDao.addOrders(customerOrders);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {InsufficientAmount.class, InsufficientOrders.class, InsufficientQuantity.class}, noRollbackFor = {ArithmeticException.class})
	@Override
	public void buyMany(Integer wid, Integer... bids)
			throws InsufficientAmount, InsufficientQuantity, InsufficientOrders {
		String wName = bookDao.getWalletDetail().stream().filter(e -> Integer.valueOf(e.getWid()).equals(wid))
				.findFirst().get().getWname();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		List<Integer> bidsList = Stream.of(bids).collect(Collectors.toList());
		Iterator<Integer> bidsIterator = bidsList.stream().distinct().iterator();
		while (bidsIterator.hasNext()) {
			Integer bid = bidsIterator.next();
			Integer price = bookDao.getPrice(bid);
			Integer amount = Collections.frequency(bidsList, bid);
			String bName = bookDao.getBooksDetail().stream().filter(e -> Integer.valueOf(e.getBid()).equals(bid))
					.findFirst().get().getBname();
			CustomerOrders customerOrders = new CustomerOrders();
			customerOrders.setP_id(bid);
			customerOrders.setU_id(wid);
			customerOrders.setReceiverInfo(wName);
			customerOrders.setpName(bName);
			customerOrders.setTotalPrice(price * amount);
			customerOrders.setQuantity(amount);
			customerOrders.setOrderTime(dateTime);			
			bookDao.updateStock(bid, amount);
			bookDao.updateWallet(wid, price*amount);
			ordersDao.addOrders(customerOrders);
		}
		
	}

	@Override
	public String getNameDetail(Integer wid, Integer bid) {
		return bookDao.getWalletDetail().stream().filter(e -> Integer.valueOf(e.getWid()).equals(wid)).findFirst().get()
				.getWname();
	}

}

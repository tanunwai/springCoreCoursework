package com.homework.springCoreCoursework.homework04.tx.dao;

import java.util.List;

import com.homework.springCoreCoursework.homework04.tx.entity.Books;
import com.homework.springCoreCoursework.homework04.tx.entity.Wallet;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientAmount;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientQuantity;


public interface BookDao {
	List<Books> getBooksDetail();
	List<Wallet> getWalletDetail();
	Integer getPrice(Integer bid);
	Integer getStockAmount(Integer bid);
	Integer getWalletMoney(Integer wid);
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity;//減去庫存
	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount;//減去餘額
}

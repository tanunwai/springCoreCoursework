package com.homework.springCoreCoursework.homework04.tx.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.homework.springCoreCoursework.homework04.tx.entity.Books;
import com.homework.springCoreCoursework.homework04.tx.entity.Wallet;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientAmount;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientQuantity;

@Repository
public class BookDaoImpl implements BookDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Books> getBooksDetail() {
		String bookSql="select * from finweb.book";
		return jdbcTemplate.query(bookSql, (rs,rowNum)->new Books(rs.getInt("bid"), 
															rs.getString("bname"),
															rs.getInt("price")));
	}

	@Override
	public List<Wallet> getWalletDetail() {
		String walletSql="select * from finweb.wallet";
		return jdbcTemplate.query(walletSql, (rs,rowNum)->new Wallet(rs.getInt("wid"),
																rs.getString("wname"),
																rs.getInt("money")));
	}

	@Override
	public Integer getPrice(Integer bid) {
		String sql="select price from book where bid=?";		
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql="select amount from stock where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql="select money from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity {
		Integer new_amount=getStockAmount(bid);
		if (new_amount <= 0) {
			throw new InsufficientQuantity(String.format("此書號: %d 目前沒庫存，目前數量: %d", bid, new_amount));
		}else if(new_amount < amount) {
			throw new InsufficientQuantity(String.format("此書號: %d 目前沒庫存，目前數量: %d，欲購買數量: %d", bid, new_amount, amount));
		}
		String sql="update stock set amount=amount-? where bid=?";
		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount {
		Integer new_money=getWalletMoney(wid);				
		if (new_money <= 0) {
			throw new InsufficientAmount(String.format("錢包號碼：%d 目前沒餘額，目前餘額：$ %d", wid, new_money));
		}else if(new_money < money) {
			throw new InsufficientAmount(String.format("錢包號碼：%d 餘額不足，目前餘額：$ %d，欲扣款金額：$ %d", wid, new_money, money));
		}
		
		String sql="update wallet set money=money-? where wid=?";
		return jdbcTemplate.update(sql, money, wid);
	}
}
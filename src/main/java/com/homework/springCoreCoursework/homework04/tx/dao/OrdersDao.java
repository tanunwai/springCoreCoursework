package com.homework.springCoreCoursework.homework04.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.homework.springCoreCoursework.homework04.tx.entity.CustomerOrders;
import com.homework.springCoreCoursework.homework04.tx.exception.InsufficientOrders;

@Component
public class OrdersDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer addOrders(CustomerOrders orders) throws InsufficientOrders{
		String inserSql="insert into finweb.order_log(p_id, u_id, receiverinfo, pname, totalprice, quantity, ordertime) values(?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(inserSql, orders.getP_id(), orders.getU_id(), orders.getReceiverInfo(),
												orders.getpName(), orders.getTotalPrice(),orders.getQuantity(),
												orders.getOrderTime());
	}
}
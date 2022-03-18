package com.homework.springCoreCoursework.homework05.invoice.dao;

import java.util.List;

import com.homework.springCoreCoursework.homework05.invoice.entity.Invoice;
import com.homework.springCoreCoursework.homework05.invoice.entity.ItemProduct;

public interface IBillInfoDao {

	List<ItemProduct> queryAllItemProduct(Integer record);
	List<Integer> getAmountFromInvoice();
	double getTotalPriceByInvoice(Invoice invoice);
	double getTotalAmountFromItemProduct(ItemProduct itemProduct);
	double getTotalMoneyFromItemProduct(ItemProduct itemProduct);
	double getMaxInvoice(Invoice invoice);
}

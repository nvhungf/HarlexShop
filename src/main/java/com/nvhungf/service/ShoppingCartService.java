package com.nvhungf.service;

import java.util.Collection;

import com.nvhungf.bean.CartItem;


public interface ShoppingCartService {

	int getCount();

	double getAmount();

	void update(int id, int quantity);

	void clear();

	Collection<CartItem> getCartItems();

	void remove(String id);

	void add(CartItem item);
	
}

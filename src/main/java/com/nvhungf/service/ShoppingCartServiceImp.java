package com.nvhungf.service;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.nvhungf.bean.CartItem;






@SessionScope
@Service
public class ShoppingCartServiceImp  implements ShoppingCartService{
	Map<String, CartItem> maps = new HashMap<String, CartItem>();
	
	@Override
	public void add(CartItem item) {
		CartItem existedItem = maps.get(item.getId());
		if (existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + 1);
//			existedItem.setProductId(item.getProductId()+existedItem.getProductId());
		}else {
			item.setQuantity(1);
			maps.put(item.getId(), item);
		}
	}
	
	@Override
	public void remove(String id) {
		maps.remove(id);
	}
	@Override
	public Collection<CartItem> getCartItems(){
		return maps.values();
	}
	@Override
	public void clear() {
		maps.clear();
	}
	@Override
	public void update(int id, int quantity) {
		CartItem item = maps.get(id);
		
		item.setQuantity(quantity);
		
		if(item.getQuantity() <= 0) {
			maps.remove(id);
		}
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();
	}
	@Override
	public int getCount() {
		if(maps.isEmpty()) {
			return 0;
		}
		return maps.values().size();
	}
}
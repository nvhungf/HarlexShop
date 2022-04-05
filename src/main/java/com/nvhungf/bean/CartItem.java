package com.nvhungf.bean;

import com.nvhungf.bean.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	String id;
	String name;
	float price;
	int Quantity;
	String image;
	String leght;
	String color;
}

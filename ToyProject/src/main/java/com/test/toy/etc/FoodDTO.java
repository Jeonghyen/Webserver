package com.test.toy.etc;

import lombok.Data;

@Data
public class FoodDTO {
	
	private String seq;
	private String name;
	private String lat;
	private String lng;
	private String star;
	private String category;
	
	private String marker;
	private String icon;
	
}

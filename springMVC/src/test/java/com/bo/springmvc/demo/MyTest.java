package com.bo.springmvc.demo;

import java.util.regex.Pattern;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月8日　上午11:21:42
 */
public class MyTest {
	public static void main(String[] args){
		String test  = "com.bo.demo.service.impl.BankService.batchUpdate";
		Pattern pattern = Pattern.compile("com.bo.demo.service.impl.*");
		System.out.println(pattern.matcher(test).matches());
	}
}

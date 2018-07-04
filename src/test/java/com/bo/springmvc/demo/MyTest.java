package com.bo.springmvc.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月8日　上午11:21:42
 */
public class MyTest {
	public static void main(String[] args){
		String test  = "com.bo.spring.service.impl.BankService.batchUpdate";
		Pattern pattern = Pattern.compile("com.bo.spring.service.impl.*");
		System.out.println(pattern.matcher(test).matches());
		System.out.println(pattern.getClass().getSimpleName());
		System.out.println(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1530695885349l);
		System.out.println(sdf.format(calendar.getTime()));
		
	}
}

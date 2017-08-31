package com.bo.springmvc.junit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description
 * @author 王博
 * @version 2017年8月24日　下午2:18:06
 */
public class CalculateTest {

	private Calculate calculate = new Calculate();

	/**
	 * Test method for {@link com.bo.springmvc.junit.Calculate#add(int, int)}.
	 */
	@Test
	public void testAdd() {
		int result = calculate.add(2, 3);
		Assert.assertEquals("加法有问题", 5, result);
		/*
		 * "加法有问题"：期望值和实际值不一致时，显示的信息 
		 * 5 ：期望值 
		 * result ：实际值
		 */
	}

	/**
	 * Test method for
	 * {@link com.bo.springmvc.junit.Calculate#substract(int, int)}.
	 */
	@Test
	public void testSubstract() {
        int result = calculate.substract(12, 2);   
        Assert.assertEquals("减法有问题", 10000, result); //故意设置减法期望值为10000  
	}

	/**
	 * Test method for
	 * {@link com.bo.springmvc.junit.Calculate#multiply(int, int)}.
	 */
	@Test
	public void testMultiply() {
		int result = calculate.multiply(2, 3);  
        Assert.assertEquals("乘法有问题", 6, result);
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.Calculate#divide(int, int)}.
	 */
	@Test
	public void testDivide() {
		int result = calculate.divide(6, 0);  
        Assert.assertEquals("除法有问题", 2, result);
	}

}

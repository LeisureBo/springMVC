package com.bo.springmvc.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Description Junit注解测试
 * @author 王博
 * @version 2017年8月24日　下午3:21:58
 */
public class JunitAnotationTest {
	
	private static JunitAnotation jAnotation;
	
	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jAnotation = new JunitAnotation();
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jAnotation = null;
		System.gc();
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.JunitAnotation#divide(int, int)}.
	 * 已断言会出现算术异常，所以在Junit测试中不会报错
	 */
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		int result = jAnotation.divide(6, 0);
		assertEquals("除法有问题", 0, result);
		fail("除法被除数为0没有抛出异常");
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.JunitAnotation#loop()}.
	 */
	@Ignore
	@Test(timeout=2000)
	public void testLoop() {
		jAnotation.loop();
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.JunitAnotation#readFile()}.
	 */
	@Test(timeout=3000)
	public void testReadFile() {
		jAnotation.readFile();
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.JunitAnotation#ignore()}.
	 */
	@Ignore("忽略的方法")
	@Test
	public void testIgnore() {
		jAnotation.ignore();
	}

	@Test // 测试排序算法性能
	public void testSort(){
		int[] arr = new int[50000];
		// 随机生成数组元素
		Random random = new Random();
		for(int i = 0; i < arr.length; i++){
			arr[i] = random.nextInt(arr.length);
		}
//		jAnotation.bubbleSort(arr);// 超时 3.818s
		jAnotation.quickSort(arr, 0, arr.length-1);// 0.004s
		for(int x : arr){
			System.out.print(x+".");
		}
	}
	
	public static void main(String[] args){
		List list = Arrays.asList(new Object[][]{
				{3,1,2},
				{4,2,2},
				{10,20,-9}
		});
		System.out.println(list);
	}
}

package com.bo.springmvc.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @Description Junit工作流程测试
 * @author 王博
 * @version 2017年8月24日　下午2:53:26
 */
public class JunitFlowTest {
	
	private static JunitFlow jFlow;
	
	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() called...");
		jFlow = new JunitFlow();
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass() called...");
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp() called...");
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown() called...");
	}

	/**
	 * Test method for {@link com.bo.springmvc.junit.JunitFlow#saySomething(java.lang.String)}.
	 */
	@Test
	public void testSaySomething() {
		jFlow.saySomething("I'm tired");
	}

	@Test
	public void testEatPeach(){
		jFlow.eatPeach("big");
	}
}

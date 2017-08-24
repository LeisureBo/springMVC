package com.bo.springmvc.service.impl;

import static org.junit.Assert.*;

import javax.enterprise.context.spi.Context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.springmvc.model.User;
import com.bo.springmvc.service.UserService;

/**
 * @Description 
 * @author 王博
 * @version 2017年8月24日　下午7:30:29
 */
public class UserServiceImplTest {

	private static ApplicationContext ctx;
	
	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	 * Test method for {@link com.bo.springmvc.service.impl.UserServiceImpl#txUpdateUser(com.bo.springmvc.model.User, com.bo.springmvc.model.User)}.
	 */
	@Test
	public void testTxUpdateUser() {
		UserService userService = (UserService) ctx.getBean("userService");
		User u1 = userService.findUserByUsername("bo");
		u1.setAge(23);
		User u2 = userService.findUserByUsername("sunjian");
		u2.setPassword("123");
//		int ret = userService.txUpdateUser(u1, u2);
		int ret = userService.txUpdateUserAndCatch(u1, u2);
		System.out.println("result:"+ret);
	}

}

package com.bo.springmvc.utils;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description 
 * @author 王博
 * @version 2018年7月2日　下午11:55:54
 * @码云 https://gitee.com/LeisureBo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class JwtTokenUtilsTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Before
	public void setUp() {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
	}
	
	@Test
	public void testGetToken() {
		String token = jwtTokenUtils.getAccessToken("bo");
		System.out.println(token);
	}

}

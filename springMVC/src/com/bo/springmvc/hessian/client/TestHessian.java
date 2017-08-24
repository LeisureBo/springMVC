package com.bo.springmvc.hessian.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.springmvc.hessian.service.BoService;

/**
 * @Description 
 * @author 王博
 * @version 2017年8月22日　下午5:40:30
 */
public class TestHessian {
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BoService bs = (BoService) ctx.getBean("boService");
		System.out.println(bs.sayHello("hello bo!"));
	}
}

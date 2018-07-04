package com.bo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description spring 测试类
 * @author 王博
 * @version 2017年3月20日　下午2:31:54
 */
@Controller
@RequestMapping("/test")
public class HelloWorld {
	
	@RequestMapping("/sayHi")
	public ModelAndView sayHello(){
		System.out.println("Controller:HelloWorld execute..");
		//1、收集参数  
        //2、绑定参数到命令对象  
        //3、调用业务对象  
        //4、选择下一个页面
		ModelAndView mv = new ModelAndView("success");//初始化时指定视图
		//mv.setViewName("success");//视图解析器会根据该名字解析到具体的视图页面 
		//添加模型数据 可以是任意的POJO对象  
		mv.addObject("message", "欢迎回来");
		return mv;
	}
}

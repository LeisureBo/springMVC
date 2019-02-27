package com.bo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	// SpringMVC允许直接返回一个String数据，作为视图名称。不需要数据模型。
	@RequestMapping(value = "test3")
	public String test01() {
		// 如果只是访问页面，就不需要ModelAndView对象，直接返回一个字符串即可
		return "index"; // 访问的是index.jsp页面
	}

	// 也可以把视图和模型分离，只需要Model中的数据
	@RequestMapping(value = "test5")
	public String test02(Model model) {
		model.addAttribute("msg", "我试试Model好不好用");
		return "index"; // 访问的是index.jsp页面
	}
	
	// 演示：重定向
	@RequestMapping("/showRedirect")
	public String showRedirect(){
		// 返回值以redirect:开头就是重定向，但是后面必须跟上URI路径而不是视图名
		return "redirect:/test/showRedirect.jsp";
	}

	// 演示：请求转发
	@RequestMapping("/showForward")
	public String showForward(){
		// 返回值以forward:开头就是请求转发，但是后面必须跟上URI路径而不是视图名
		return "forward:/test/showForward.jsp";
	}
	
}

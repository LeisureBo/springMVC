package com.bo.controller;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bo.model.User;
import com.bo.service.UserService;

/**
 * @Description 控制层
 * @author 王博
 * @version 2017年3月20日　下午5:40:16
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Value("${home.country.code}")
	private String countryCode;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(User user){
		System.out.println(user.toString()+"login..");
		System.out.println("countryCode:"+countryCode);
		ModelAndView mv = new ModelAndView("redirect:/index.jsp");
		User u = userService.findUserByUsername(user.getUsername());
		if(u != null){
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())){
				mv.setViewName("success");
				mv.addObject("user", user);
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(ServletRequest request, User user){
		System.out.println("register..");
		ModelAndView mv = new ModelAndView("error");
		//通过request获取各参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		System.out.println("username:"+username+"--password:"+password+"--age:"+age);
		User u = userService.findUserByUsername(user.getUsername());
		//用户不存在执行新增
		if(u == null){
			int result = userService.saveOrUpdateUser(user);
			if(result > 0){
				mv.setViewName("success");
			}
		}
		return mv;
	}
	
}

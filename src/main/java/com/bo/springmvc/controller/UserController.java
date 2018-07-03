package com.bo.springmvc.controller;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bo.springmvc.anno.Anonymous;
import com.bo.springmvc.constants.ResponseCodeEnum;
import com.bo.springmvc.controller.support.ResponseData;
import com.bo.springmvc.dto.UserLoginRequest;
import com.bo.springmvc.dto.UserLoginResponse;
import com.bo.springmvc.model.User;
import com.bo.springmvc.service.UserService;

/**
 * @Description 控制层
 * @author 王博
 * @version 2017年3月20日　下午5:40:16
 */
@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@Value("${home.country.code}")
	private String countryCode;
	
	@Anonymous
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView doLogin(UserLoginRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("redirect:/index.jsp");
		UserLoginResponse userLoginResponse = userService.login(request);
		// 将token返回给客户端
		response.addHeader("Set-Cookie",
                "access_token="+userLoginResponse.getToken()+";Path=/;HttpOnly");
		mv.addObject("resp", new ResponseData(userLoginResponse.getRetCode(), userLoginResponse.getRetMsg()));
		if(userLoginResponse.getRetCode().equals(ResponseCodeEnum.SUCCESS.getCode())) {
			mv.setViewName("success");
		}
		return mv;
	}
	
	@Anonymous
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(ServletRequest request, User user){
		ModelAndView mv = new ModelAndView("error");
		//通过request获取各参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		System.out.println("[username="+username+", password="+password+", age="+age+"] register..");
		User u = userService.findUserByUsername(user.getUsername());
		//用户不存在执行新增
		if(u == null){
			if(!StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())){
				int result = userService.saveOrUpdateUser(user);
				if(result > 0){
					mv.setViewName("success");
				}
			}
		}
		return mv;
	}
	
	
}

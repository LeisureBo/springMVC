package com.bo.springmvc.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bo.springmvc.anno.Anonymous;
import com.bo.springmvc.constants.ResponseCodeEnum;
import com.bo.springmvc.controller.BaseController;
import com.bo.springmvc.controller.support.ResponseData;
import com.bo.springmvc.dto.CheckTokenRequest;
import com.bo.springmvc.dto.CheckTokenResponse;
import com.bo.springmvc.service.UserService;
import com.bo.springmvc.utils.CookieUtils;
import com.bo.springmvc.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description token认证拦截器
 * @author 王博
 * @version 2018年7月3日　下午9:37:47
 * @码云 https://gitee.com/LeisureBo
 */
public class TokenAuthInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private final String ACCESS_TOKEN = "access_token";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Resource
	private JwtTokenUtils jwtTokenUtils;
	
	@Resource
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("step into token interceptor...");
		// 排除非拦截控制层
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		if(isAnonymous(handlerMethod)) {
			return true;
		}
		
		Object object = handlerMethod.getBean();
		if(!(object instanceof BaseController)) {
			throw new RuntimeException("must extends BaseController");
		}
		
		String token = CookieUtils.getCookieValue(request, ACCESS_TOKEN);
		boolean isAjax = isAjax(request);
		// 校验请求头是否存在access_token
		if(StringUtils.isBlank(token)) {
			if(isAjax) {
				ResponseData resp = new ResponseData();
				resp.setCode(ResponseCodeEnum.NO_ACCESS_ERROR.getCode());
				resp.setMessage(ResponseCodeEnum.NO_ACCESS_ERROR.getMsg());
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(mapper.writeValueAsString(resp));
			}else {
				response.sendRedirect("/springMVC/unauthz.jsp");
			}
			return false;
		}
		// 校验token签名
		CheckTokenResponse checkTokenResponse = userService.validateToken(new CheckTokenRequest(token));
		if(checkTokenResponse.getRetCode().equals(ResponseCodeEnum.SUCCESS.getCode())) {
			((BaseController) handlerMethod.getBean()).setUid(checkTokenResponse.getUid());
			return super.preHandle(request, response, handler);
		}
		// 验证失败或token过期
		if(isAjax) {
			ResponseData resp = new ResponseData();
			resp.setCode(ResponseCodeEnum.TOKEN_SIGNATURE_ERROR.getCode());
			resp.setMessage(ResponseCodeEnum.TOKEN_SIGNATURE_ERROR.getMsg());
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(mapper.writeValueAsString(resp));
			return false;
		}
		response.sendRedirect("/springMVC/unauthz.jsp");
		return false;
		
	}
	
	private boolean isAnonymous(HandlerMethod handlerMethod) {
		BaseController bean = (BaseController) handlerMethod.getBean();
		if(bean.getClass().getAnnotation(Anonymous.class) != null) {
			return true;
		}
		Method method = handlerMethod.getMethod();
		if(method.getAnnotation(Anonymous.class) != null) {
			return true;
		}
		return false;
	}
	
	private boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  
	    && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With").toString())) ;
	}
	
}

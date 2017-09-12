package com.bo.springmvc.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bo.springmvc.model.User;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月12日　下午7:45:56
 */
public class CommonInterceptor implements HandlerInterceptor{

	private final Logger logger = Logger.getLogger(CommonInterceptor.class);
	
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 (嵌套执行)
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		logger.info("requestUri:" + requestUri);
		logger.info("contextPath:" + contextPath);
		logger.info("url:" + url);

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			logger.info("用户未登录：跳转到login页面！");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView modelAndView) throws Exception {
		logger.info("==============执行顺序: 2、postHandle================");
		if (modelAndView != null) { // 加入当前时间
			modelAndView.addObject("curTime", new Date());
		}
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		  logger.info("==============执行顺序: 3、afterCompletion================");  
	}
	
}

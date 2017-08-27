package com.bo.springmvc.hessian.exporter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianServiceExporter;

import com.bo.springmvc.util.SecurityTool;

/**
 * @Description 继承HessianServiceExporter可完成一些自定义的工作，如鉴权、预处理、日志、事务管理什么的
 * @author 王博
 * @version 2017年8月21日　上午11:09:12
 */
public class AuthHessianServiceExporter extends HessianServiceExporter {

	 // 密匙，用来验证访问者是否具有访问权限
	private static final String authorization = "ff1423a932d68c34a83ea9a6f123dccf";// 加密后的密码:20170821
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从Header里直接取出验证密匙
		String reqAuth = request.getHeader("Authorization");
		// 如果存在且符合我们的期望则允许其访问，否则可自行编辑响应报文
		if(reqAuth == null || reqAuth.isEmpty()){
			throw new ServletException("Authorization header not existing");
		}else {
			// 对比加密后的密码
			if(!authorization.equals(SecurityTool.getMD5Hash(reqAuth))){
				throw new ServletException("Unexpected authorization key");
			}
		}
		//鉴权成功，则继续流转下去。控制权交回HessianServiceExporter
		super.handleRequest(request, response);
	}

	public static String getAuthorization() {
		return authorization;
	}
	
}

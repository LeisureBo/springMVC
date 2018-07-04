package com.bo.springmvc.controller;

/**
 * @Description 控制器基类
 * @author 王博
 * @version 2018年7月3日　下午9:40:47
 * @码云 https://gitee.com/LeisureBo
 */
public class BaseController {
	
	private static ThreadLocal<String> userHolder = new ThreadLocal<>();
	
	public String getUid() {
		return userHolder.get();
	}
	
	public void setUid(String uid) {
		userHolder.set(uid);
	}
}

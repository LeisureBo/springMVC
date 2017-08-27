package com.bo.springmvc.hessian.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bo.springmvc.hessian.service.BoService;

/**
 * @Description 
 * @author 王博
 * @version 2017年8月21日　上午11:03:06
 */
@Service("boServiceImpl")
public class BoServiceImpl implements BoService {

	@Override
	public String sayHello(String name) {
		if(StringUtils.isEmpty(name)){// null || ""
			name = "anonymous";
		}
		return "hello:"+name;
	}
	
}

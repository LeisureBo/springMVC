package com.bo.springmvc.hessian.proxy;

import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @Description 
 * @author 王博
 * @version 2017年8月22日　下午7:40:12
 */
@Service("proxyFactory")
public class AuthHessianProxyFactory extends HessianProxyFactory{
	
	private final static String authorization = "20170821";
    
    @Override
    public String getBasicAuth()
    {
        return authorization;
    }
}

package com.bo.springmvc.controller.support;

import java.io.Serializable;

/**
 * @Description 客户端返回信息封装
 * @author Bo
 * @version 2018年7月3日　下午11:40:07
 * @码云 https://gitee.com/LeisureBo
 */
public class ResponseData implements Serializable {

	private static final long serialVersionUID = 625977256147055680L;

	private String code;

    private String message;

    private Object data;

    public ResponseData() {}
    
    public ResponseData(String code) {
    	this.code = code;
    }
    
    public ResponseData(String code, String message) {
    	this.code = code;
    	this.message = message;
    }
    
    public ResponseData(String code, String message, Object data) {
    	this.code = code;
    	this.message = message;
    	this.data = data;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

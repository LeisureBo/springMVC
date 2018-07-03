package com.bo.springmvc.dto;

import java.io.Serializable;

/**
 * @Description 
 * @author Bo
 * @version 2018年7月3日　下午9:55:12
 * @码云 https://gitee.com/LeisureBo
 */
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;
    private String retCode;
    private String retMsg;
	/**
	 * @return the retCode
	 */
	public String getRetCode() {
		return retCode;
	}
	/**
	 * @param retCode the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	/**
	 * @return the retMsg
	 */
	public String getRetMsg() {
		return retMsg;
	}
	/**
	 * @param retMsg the retMsg to set
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	@Override
	public String toString() {
		return "AbstractResponse [retCode=" + retCode + ", retMsg=" + retMsg + "]";
	}

}

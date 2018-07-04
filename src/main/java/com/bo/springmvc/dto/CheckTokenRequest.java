package com.bo.springmvc.dto;

/**
 * @Description 
 * @author Bo
 * @version 2018年7月3日　下午9:59:29
 * @码云 https://gitee.com/LeisureBo
 */
public class CheckTokenRequest extends AbstractRequest {

	private static final long serialVersionUID = -8430630448744935764L;
	
	private String token;

	public CheckTokenRequest() {}
	
	public CheckTokenRequest(String token) {
		this.token = token;
	}
	
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	@Override
	public String toString() {
		return "CheckTokenRequest [token=" + token + ", " + super.toString() + "]";
	}


}

package com.bo.springmvc.dto;

/**
 * @Description 
 * @author Bo
 * @version 2018年7月3日　下午9:59:54
 * @码云 https://gitee.com/LeisureBo
 */
public class CheckTokenResponse extends AbstractResponse {

	private static final long serialVersionUID = 4593910632406487206L;
	
	private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

	@Override
	public String toString() {
		return "CheckTokenResponse [uid=" + uid + ", " + super.toString() + "]";
	}

}

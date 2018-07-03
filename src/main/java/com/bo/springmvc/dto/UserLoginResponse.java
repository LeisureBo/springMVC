package com.bo.springmvc.dto;

/**
 * @Description 
 * @author 王博
 * @version 2018年7月3日　下午9:52:05
 * @码云 https://gitee.com/LeisureBo
 */
public class UserLoginResponse extends AbstractResponse {

	private static final long serialVersionUID = -7795368597804913052L;
	
    private Integer uid;
    private String avatar;
    private String mobile;
    private String token;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	@Override
	public String toString() {
		return "UserLoginResponse [uid=" + uid + ", avatar=" + avatar + ", mobile=" + mobile + ", token=" + token
				+ ", " + super.toString() + "]";
	}

}

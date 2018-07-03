package com.bo.springmvc.dto;

/**
 * @Description
 * @author 王博
 * @version 2018年7月3日 下午9:51:04
 * @码云 https://gitee.com/LeisureBo
 */
public class UserLoginRequest extends AbstractResponse {

	private static final long serialVersionUID = -4348505782077265900L;

	private String username;
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

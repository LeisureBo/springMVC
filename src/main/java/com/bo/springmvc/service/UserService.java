package com.bo.springmvc.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.bo.springmvc.dto.CheckTokenRequest;
import com.bo.springmvc.dto.CheckTokenResponse;
import com.bo.springmvc.dto.UserLoginRequest;
import com.bo.springmvc.dto.UserLoginResponse;
import com.bo.springmvc.model.User;

/**
 * @Description 
 * @author 王博
 * @version 2017年3月20日　下午5:36:14
 */
public interface UserService {
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	public UserLoginResponse login(UserLoginRequest request);
	
    /**
     * 校验Token
     * @param request
     * @return
     */
    public CheckTokenResponse validateToken(CheckTokenRequest request);
	
	/**
	 * @Description 取得所有用户
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * @Description 按id取得用户
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	
	/**
	 * @Description 按username取得用户
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	
	/**
	 * @Description 保存或更新用户
	 * @param user
	 */
	public int saveOrUpdateUser(User user);
	
	/**
	 * @Description 删除用户
	 * @param user
	 * @return
	 */
	public int deleteUserById(Integer id);
	
	
	public int txUpdateUser(User u1, User u2) throws FileNotFoundException;// 事务测试1
	
	public int txUpdateUserAndCatch(User u1, User u2);// 事务测试2

	public int NonTransactionUpdate(User u1, User u2);// 非事务测试
}

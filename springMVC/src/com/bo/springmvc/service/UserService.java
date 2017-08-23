package com.bo.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bo.springmvc.model.User;

/**
 * @Description 
 * @author 王博
 * @version 2017年3月20日　下午5:36:14
 */
public interface UserService {
	
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
	
	
}

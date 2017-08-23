package com.bo.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.springmvc.mapper.UserMapper;
import com.bo.springmvc.model.User;
import com.bo.springmvc.service.UserService;

/**
 * @Description 
 * @author 王博
 * @version 2017年3月30日　上午11:18:56
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> findAllUser() {
		return userMapper.selectAllUser();
	}

	@Override
	public User findUserById(Integer id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public User findUserByUsername(String username) {
		return userMapper.selectUserByUsername(username);
	}

	@Override
	public int saveOrUpdateUser(User user) {
		int result = 0;
		try {
			User u = userMapper.selectUserById(user.getId());
			if (u != null) {
				result = userMapper.updateUserById(user);
			} else {
				result = userMapper.insertUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		return result;
	}

	@Override
	public int deleteUserById(Integer id) {
		int result = 0;
		try {
			result = userMapper.deleteUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
}

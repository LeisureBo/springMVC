package com.bo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bo.model.User;

/**
 * @Description Mapper映射类(相当于MVC Dao层)
 * @author 王博
 * @version 2017年3月28日　下午7:44:14
 */
public interface UserMapper {
	
	/**
	 * @Description 查询所有用户
	 * @return
	 */
	public List<User> selectAllUser();
	
	/**
	 * @Description 根据用户账号id查询用户
	 * @param id
	 * @return
	 */
	public User selectUserById(Integer id);
	
	/**
	 * @Description 根据username查询用户
	 * @param username
	 * @return
	 */
	public User selectUserByUsername(String username);
	
	/**
	 * @Description 添加用户
	 * @param user
	 */
	public int insertUser(User user);
	
	/**
	 * @Description 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserById(User user);
	
	/**
	 * @Description 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUserById(Integer id);
}

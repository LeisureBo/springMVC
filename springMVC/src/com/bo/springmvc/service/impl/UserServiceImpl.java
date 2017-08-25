package com.bo.springmvc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int txUpdateUser(User u1, User u2) throws FileNotFoundException {// 事务性
		int ret1 = userMapper.updateUserById(u1);// 因后面的异常而回滚
//		int i = 1 / 0;// 抛出运行时异常，事务回滚
		FileInputStream fis = new FileInputStream(new File("D:/test/a.txt"));// 抛出编译时异常,若指定@Transactional(rollbackFor=Exception.class),则事务回滚;
		int ret2 = userMapper.updateUserById(u2);// 未执行
		if(ret1 == 1 && ret2 == 1){
			return 1;
		}
		return 0;
	}
	
	@Transactional
	@Override
	// 事务性操作，不可catch Exception或RuntimeException而不抛出，导致外围框架捕获不到异常，认为执行正确而提交。
	public int txUpdateUserAndCatch(User u1, User u2){  
		try {
			int ret1 = userMapper.updateUserById(u1);// 执行成功
			int i = 1 / 0;// 运行时异常
			int ret2 = userMapper.updateUserById(u2);// 未执行
			if(ret1 == 1 && ret2 == 1){
				return 1;
			}
		} catch (Exception e) { // 所有异常被捕获而未抛出
			// 在此手动抛出[非编译性异常]使spring接收到异常可回滚
//			throw e;
			// 或者手动回滚
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 0;
	}
	
	// 非事务方法中调用事务方法测试:事务不会回滚
	@Override
	public int NonTransactionUpdate(User u1, User u2){
		// 1.txUpdate方法内发生异常,事务没有回滚.
//		return this.txUpdateUser(u1, u2);
		// 2.txUpdateUserAndCatch方法内捕获并抛出异常，事务没有回滚.
		// 3.txUpdateUserAndCatch方法catch中手动回滚事务失败:org.springframework.transaction.NoTransactionException: No transaction aspect-managed TransactionStatus in scope
		return this.txUpdateUserAndCatch(u1, u2);
	}
	
}

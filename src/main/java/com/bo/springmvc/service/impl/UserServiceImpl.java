package com.bo.springmvc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bo.springmvc.constants.ResponseCodeEnum;
import com.bo.springmvc.dto.CheckTokenRequest;
import com.bo.springmvc.dto.CheckTokenResponse;
import com.bo.springmvc.dto.UserLoginRequest;
import com.bo.springmvc.dto.UserLoginResponse;
import com.bo.springmvc.exception.ExceptionUtils;
import com.bo.springmvc.exception.ServiceException;
import com.bo.springmvc.exception.ValidateException;
import com.bo.springmvc.mapper.UserMapper;
import com.bo.springmvc.model.User;
import com.bo.springmvc.service.UserService;
import com.bo.springmvc.utils.JwtTokenUtils;

/**
 * @Description 
 * @author 王博
 * @version 2017年3月30日　上午11:18:56
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private UserMapper userMapper;

	@Resource
	private JwtTokenUtils jwtTokenUtils;
	
	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		logger.info("login request --> " + request);
		UserLoginResponse response = new UserLoginResponse();
		try {
			// 登录验证
			beforeLogin(request);
			User user = userMapper.selectUserByUsername(request.getUsername());
			if(user == null || !user.getPassword().equals(request.getPassword())) {
				response.setRetCode(ResponseCodeEnum.USER_OR_PASSWORD_ERRROR.getCode());
                response.setRetCode(ResponseCodeEnum.USER_OR_PASSWORD_ERRROR.getMsg());
                return response;
			}
			// 生成token 
			String token = jwtTokenUtils.getAccessToken(String.valueOf(user.getId()));
			if(token == null) throw new ValidateException(ResponseCodeEnum.GENERATE_TOKEN_ERROR.getCode(),"生成token失败");
			response.setUid(user.getId());
			response.setToken(token);
			response.setRetCode(ResponseCodeEnum.SUCCESS.getCode());
			response.setRetMsg(ResponseCodeEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			logger.error("login occur exception :" + e);
			ServiceException serviceException = (ServiceException) ExceptionUtils.handlerException4biz(e);
			response.setRetCode(serviceException.getErrorCode());
			response.setRetMsg(serviceException.getErrorMsg());
		}
		logger.info("login response --> " + response);
		return response;
	}

	@Override
	public CheckTokenResponse validToken(CheckTokenRequest request) {
		logger.info("valid token request --> " + request);
		CheckTokenResponse response = new CheckTokenResponse();
		try {
			beforeValidToken(request);
			String uid = jwtTokenUtils.verifyToken(request.getToken());
			if(uid == null) throw new ValidateException(ResponseCodeEnum.SIGNATURE_ERROR.getCode(), "解析token失败");
			response.setUid(uid);
			response.setRetCode(ResponseCodeEnum.SUCCESS.getCode());
			response.setRetMsg(ResponseCodeEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			ServiceException serviceException = (ServiceException) ExceptionUtils.handlerException4biz(e);
			response.setRetCode(serviceException.getErrorCode());
			response.setRetMsg(serviceException.getErrorMsg());
		}
		logger.info("valid token response --> " + response);
		return response;
	}
	
	private void beforeValidToken(CheckTokenRequest request) {
		if(request==null){
            throw new ValidateException("请求对象为空");
        }
        if(StringUtils.isEmpty(request.getToken())){
            throw new ValidateException("token信息为空");
        }
	}
	
	private void beforeLogin(UserLoginRequest request) {
		if(request == null) {
			throw new ValidateException(ResponseCodeEnum.SYS_PARAM_ERROR.getCode(), "请求参数为空");
		}
		if(StringUtils.isBlank(request.getUsername())) {
			throw new ValidateException(ResponseCodeEnum.SYS_PARAM_ERROR.getCode(), "用户名为空");
		}
		if(StringUtils.isBlank(request.getPassword())) {
			throw new ValidateException(ResponseCodeEnum.SYS_PARAM_ERROR.getCode(), "密码为空");
		}
	}
	
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

	@Transactional(rollbackFor=Exception.class)
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

package com.bo.springmvc.utils;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description Json web token utils
 * @Author 王博
 * @Version 2018年7月2日　下午3:18:11
 * @码云 https://gitee.com/LeisureBo
 */
@Component("jwtTokenUtils")
public class JwtTokenUtils {
	
	private String ISSUER = "companyName"; // 机构/发行人
	
	private String APP_SECRET_KEY = "secret_key";// 密钥
	
	private long MAX_TOKEN_AGE = 300; // 存活期/秒
	
	private String ALGORITHM = "HmacSHA256";// 加密算法
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * @Description 根据用户ID生成Token
	 * @param userId
	 * @return
	 */
	public String getAccessToken(String userId) {
		Map<String, String> claims = new HashMap<>();
		claims.put("iss", ISSUER);
		claims.put("uid", userId);
		return generateToken(claims, APP_SECRET_KEY);
	}
	
	/**
	 * @Description 解密JWT并返回uid
	 * @param token
	 * @return
	 */
	public String verifyToken(String token) {
		String uid = null;
		try {
			String[] strs = token.split("\\.");
			String headerAndPayload = strs[0] + "." + strs[1];
			String calculatedSignature = signHmac256(headerAndPayload, APP_SECRET_KEY);
			String clientSignature = strs[2];
			// 校验数据是否被篡改
			if(calculatedSignature.equals(clientSignature)) {
				String headerJson = new String(Base64.decodeBase64(strs[0]), "UTF-8");
				Map<String, String> header = mapper.readValue(headerJson, Map.class);
				long exp = Long.valueOf(header.get("exp"));
				// 验证token的有效期
				if(System.currentTimeMillis() <= exp) {
					String payloadJson = new String(Base64.decodeBase64(strs[1]), "UTF-8");
					Map<String, String>	payload = mapper.readValue(payloadJson, Map.class);
					uid = payload.get("uid");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return uid;
	}
	
	
	/**
	 * @Description 生成JWT token
	 * @param payload
	 * @param secretKey
	 * @return
	 */
	private String generateToken(Map<String, String> payload, String appSecretKey) {
		String headerAndPayload = getHeaderAndPayload(payload);
		String signature = signHmac256(headerAndPayload, appSecretKey);
		return headerAndPayload + "." + signature;
	}
	
	
	/**
	 * @Description 拼接请求头和消息体
	 * @param payload
	 * @return
	 */
	private String getHeaderAndPayload(Map<String, String> payload) {
		Map<String, String> header = new HashMap<>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");
		header.put("exp", String.valueOf(System.currentTimeMillis() + MAX_TOKEN_AGE * 1000L));
		String headerJson, payloadJson = null;
		try {
			headerJson = mapper.writeValueAsString(header);
			payloadJson = mapper.writeValueAsString(payload);
			return Base64.encodeBase64URLSafeString(headerJson.getBytes()) + 
					"." + Base64.encodeBase64URLSafeString(payloadJson.getBytes());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * @Description 使用HMAC256加密算法生成签名
	 * @param headerAndPayload
	 * @param appSecretKey
	 * @return signature
	 */
	private String signHmac256(String headerAndPayload, String appSecretKey) {
		SecretKey secretKey = null;
		String result = null;
		Mac mac = null;
		try {
			secretKey = new SecretKeySpec(appSecretKey.getBytes("UTF-8"), ALGORITHM);
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			result = Base64.encodeBase64URLSafeString(mac.doFinal(headerAndPayload.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}

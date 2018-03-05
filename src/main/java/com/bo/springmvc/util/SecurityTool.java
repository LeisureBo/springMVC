package com.bo.springmvc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @author 王博
 * @version 2017年8月21日 上午10:58:13
 */
public class SecurityTool {
	
	// 十六进制下数字到字符的映射数组
	public static final char[] table = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String getMD5Hash(String plain) {
		try {
			byte[] bytes = plain.getBytes();
			// 创建具有指定算法名称的信息摘要
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
			md5.update(bytes);
			byte[] md5bytes = md5.digest();
			char[] hashcode = new char[md5bytes.length * 2];
			for (int i = 0, j = 0; i < md5bytes.length; i++) {
				byte bt = md5bytes[i];
				hashcode[j++] = table[bt >>> 4 & 0xf];
				hashcode[j++] = table[bt & 0xf];
			}
			// 将得到的字节数组变成字符串返回  
			return new String(hashcode);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String psw = "20170821";
		System.out.println(getMD5Hash(psw));
	}

}

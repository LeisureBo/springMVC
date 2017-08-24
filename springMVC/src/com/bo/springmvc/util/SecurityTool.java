package com.bo.springmvc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 
 * @author 王博
 * @version 2017年8月21日　上午10:58:13
 */
public class SecurityTool
{
    public static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    public static String getMD5Hash(String plain)
    {
        byte[] bytes = plain.getBytes();
        MessageDigest md5;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        byte[] md5bytes = md5.digest(bytes);
        char[] hashcode = new char[md5bytes.length * 2];
        for (int i=0, j=0; i<md5bytes.length; i++) 
        {
           byte bt = md5bytes[i];
           hashcode[j++] = table[bt >>>4 & 0xf];
           hashcode[j++] = table[bt & 0xf];
        }
        return new String(hashcode);
    }
    
    public static void main(String[] args){
    	String psw = "20170821";
    	System.out.println(getMD5Hash(psw));
    }
    
}

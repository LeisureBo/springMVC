package com.bo.springmvc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @Description 
 * @Author Bo
 * @Version 2018年7月4日　下午1:38:57
 * @码云 https://gitee.com/LeisureBo
 */
public class CookieUtils {
	
	private static final Logger log = Logger.getLogger(CookieUtils.class);

	public static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isNotEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equals(cookie.getName(), key)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}


	public static Cookie genCookieWithDomain(String key, String value, int maxAge, String domain){
		Cookie cookie = new Cookie(key, value);
		enrichCookie(cookie, "/", maxAge, domain);
		return cookie;
	}

	public static Cookie genCookie(String key, String value, String uri, int maxAge, String domain){
		Cookie cookie = new Cookie(key, value);
		enrichCookie(cookie, uri, maxAge, domain);
		return cookie;
	}

	public static void enrichCookie(Cookie cookie, String uri, int maxAge, String domain){
		log.debug("cookie path is=[" + uri + "], maxAge=[" + maxAge + "], domain=[" + domain + "]");
		cookie.setPath(uri);
		cookie.setMaxAge(maxAge);
		cookie.setDomain(domain);
	}

	public static void setCookie(HttpServletResponse response, Cookie cookie){
		response.addCookie(cookie);
	}
}

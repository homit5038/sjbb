package com.xqx.frame.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.xqx.frame.model.TUser;

/**
 * 
 * @author yyhua
 * 
 * @since 2016-7-15
 * 
 * @Description spring security 工具类
 */
public class SecurityUtil {

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static UserDetails getCurrentUserDetials() {
		Object o = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (o instanceof UserDetails) {
			return (UserDetails) o;
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static TUser getCurrentUser() {
		return (TUser)SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}

	/**
	 * 判断用户是否有某个权限
	 * 
	 * @param role
	 * @return
	 */
	public static boolean hasRole(String role) {
		// 空值直接返回否
		if (StringUtils.isEmpty(role)) {
			return false;
		}

		// 遍历当前用户权限并判断
		Collection<? extends GrantedAuthority> authorities = getCurrentUserDetials()
				.getAuthorities();
		for (GrantedAuthority a : authorities) {
			if (role.equals(a.getAuthority())) {
				// 权限判断肯定
				return true;
			}
		}

		// 判断否定
		return false;
	}

	/**
	 * 判断给定的用户id是否为当前登录用户
	 * 
	 * @param userId
	 * @return
	 */
	public static boolean isMe(long userId) {
		long myId = getCurrentUser().getId();
		return myId == userId;
	}

	public static long getUserId() {
		return getCurrentUser().getId();
	}
}

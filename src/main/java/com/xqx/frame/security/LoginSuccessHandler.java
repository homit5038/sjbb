package com.xqx.frame.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.xqx.frame.model.TUser;

/**
 * 
 * @author yyhua
 * 
 * @since 2016-7-15
 * 
 * @Description 登陆成功后置处理器
 */
@Service
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	/**
	 * 登陆成功后处理内容
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		// 页面跳转
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	/**
	 * 判断调转地址
	 */
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		//获取客户端ip地址
		String ip = request.getRemoteAddr();
		user.setLoginIpAddress(ip);
		
		return "/home";
	}

}

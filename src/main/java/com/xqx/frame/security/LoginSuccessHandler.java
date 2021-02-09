package com.xqx.frame.security;

import com.xqx.frame.config.JWTUtil;
import com.xqx.frame.config.auth.CustomUserDetailsServiceImpl;
import com.xqx.frame.model.TUser;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 
 * @author yyhua
 * 
 * @since 2016-7-15
 * 
 * @Description 登陆成功后置处理器
 */
@Service
@Component("LoginSuccessHandler")
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	@Resource
	private JWTUtil jwtUtils;
	@Resource
	private CustomUserDetailsServiceImpl customUserDetailsService;

	/**
	 * 登陆成功后处理内容
	 */
	@Override
	@ResponseBody
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		// 页面跳转

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("===============666666===================="+principal);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		//获取客户端ip地址
		String ip = request.getRemoteAddr();
		//user.setLoginIpAddress(ip);
	    String sessionId = request.getSession().getId();
		UserDetails userDetail = customUserDetailsService.loadUserByUsername(userDetails.getUsername());
		String tokenes =  jwtUtils.generateToken(userDetail);
	    JSONObject res = new JSONObject();
	        res.put("status",1);
	        res.put("JSESSIONID",sessionId);
		    res.put("Authorization",tokenes);
	        res.put("msg","登录成阿功");
	        System.out.print(res);
	        response.setStatus(200);
	        response.setContentType("application/json;charset=UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");
		    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	        response.getWriter().append(res.toString());
		
		
		//handle(request, response, authentication);
		//clearAuthenticationAttributes(request);
		
		
		
	 /*       JSONObject res = new JSONObject();
	        res.put("success",true);
	        res.put("msg","登录成功");
	        System.out.print(res);
	        response.setStatus(200);
	        response.setContentType("application/json;charset=UTF-8");
	        response.getWriter().append(res.toString());*/


		
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
		
		return "/kindergarten/list";
	}

}

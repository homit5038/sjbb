package com.xqx.frame.mobile.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/m")
public class MHomeController {
	
	@ResponseBody
	@RequestMapping("home")
	
	
	
	public JSONObject mgotoBacks(Model m,@RequestHeader("Host") String host,
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader("Accept") String accept,
            @RequestHeader("Accept-Language") String acceptLanguage,
            @RequestHeader("Accept-Encoding") String acceptEncoding,
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("Connection") String conn,
            @CookieValue("JSESSIONID") String cookie2) {
		
		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		System.out.println(cookie);
		
		
		String loginName=SecurityUtil.getCurrentUser().getLoginName();
		Long id=SecurityUtil.getCurrentUser().getId();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("loginName", loginName);
		jsonObj.put("id", id);
		
		//m.addAttribute("msg", "THIS IS HOME PAGE");
		return jsonObj;
	}

}

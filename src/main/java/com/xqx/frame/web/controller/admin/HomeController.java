package com.xqx.frame.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;

@Controller
public class HomeController {
	

	@RequestMapping("admin/home")
	public String gotoHome() {
		return "home";
	}

	@RequestMapping("admin/right")
	public String gotoRight() {
		return "admin/right";
	}

	@RequestMapping("/home")
	public String gotoBack(Model m) {
		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		if(user == null ){
			return "../../login";
		}
		m.addAttribute("msg", "THIS IS HOME PAGE");
		return "home";
	}
	
	@RequestMapping("/homeIndex")
	public String gotoHomeIndex() {
		return "homeIndex";
	}

}

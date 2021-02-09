package com.xqx.frame.web.controller.admin;

import com.xqx.frame.service.KindergartenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class HomeController {

	@Autowired
	KindergartenService kindergartenService;

	@RequestMapping("admin/home")
	public String gotoHome() {
		return "home";
	}



	@RequestMapping("/goHome/{id}")
	public String gotoKinderGartenHome(Model m,@PathVariable Long id, HttpServletRequest request) {
		String Kindergartenname=kindergartenService.findKindergartenById(id).getKindergartenname();
		request.getSession().setAttribute("topId", id);
		m.addAttribute("kinderGarteName", Kindergartenname);
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

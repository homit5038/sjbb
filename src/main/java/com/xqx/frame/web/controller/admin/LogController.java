package com.xqx.frame.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xqx.frame.model.SystemLog;
import com.xqx.frame.service.SystemLogService;

@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	SystemLogService logService;
	
	@RequestMapping(value = "/findAllLog")
	public String findAllLog(@ModelAttribute("SystemLog") SystemLog log,
			@PageableDefault(page = 0,size = 10,sort = {"operTime"},
			direction = Direction.DESC) Pageable p,Model m,HttpServletRequest request){
		String beginTime = request.getParameter("beginTime");
		m.addAttribute("beginTime", beginTime);
		String endTime = request.getParameter("endTime");
		m.addAttribute("endTime", endTime);
		Page<SystemLog> logs = logService.findAll(beginTime,endTime, log.getUserName(), p);
		m.addAttribute("num", logs.getTotalElements());
		m.addAttribute("logs", logs);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("method", "get");
		return "/log/logList";
	}
}

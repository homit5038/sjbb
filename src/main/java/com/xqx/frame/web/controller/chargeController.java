package com.xqx.frame.web.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.SexType;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TPayedInfo;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.ChildrenService;
import com.xqx.frame.service.FileService;
import com.xqx.frame.service.GradeService;

@Controller
@RequestMapping("/charge")
public class chargeController {
	@Autowired
	ChildrenService childrenService;
	@Autowired
	TchargeItemDao chargeItemDao;
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	TPayedInfoDao payedinfoDao;
	@Autowired
	TClasseDao classesService;
	@Autowired
	FileService fileservice;
	@Autowired
    GradeService gradeService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping(value = "/chargemain")
	public String chargeMain(Model m,HttpServletRequest request){
		String name = request.getParameter("childName");
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
		List<TChargeItem> Itemlist=chargeItemDao.findAll();
		
		 List<TChildren> lists = childrenService.findChildrenByName(name);
		m.addAttribute("classe", classe);
		m.addAttribute("grade", grade);
		m.addAttribute("lists", lists);
		m.addAttribute("Itemlist", Itemlist);
		return "/charge/chargemain";
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/addchargepay")
	
	public String addChargePay(HttpServletRequest request){
		//payedinfoDao.save(choose);charge-real-pay
		TUser user = (TUser) SecurityUtil.getCurrentUser();

		
		BigDecimal shouldpay = new BigDecimal(request.getParameter("charge-should-pay"));
		BigDecimal chargerealpay = new BigDecimal(request.getParameter("charge-real-pay"));
		BigDecimal chargereturn = new BigDecimal(request.getParameter("charge-return"));
		Long childId = Long.valueOf(request.getParameter("childId"));
		String paytype = request.getParameter("paytype");
		String paystatus = request.getParameter("payStatus");
		String chargConnection = request.getParameter("chargConnection");
		String flowCode = request.getParameter("flowCode");
		String remarks = request.getParameter("bz");
		
		TChildren children=childrenDao.findOne(childId);
		TPayedInfo PayedInfo=new  TPayedInfo();
		PayedInfo.setChargeshouldpay(shouldpay);
		PayedInfo.setChargerealpay(chargerealpay);
		PayedInfo.setUser(user);
		PayedInfo.setChargConnection(chargConnection);
		PayedInfo.setChargereturn(chargereturn);
		PayedInfo.setChildren(children);
		PayedInfo.setPaytype(paytype);
		PayedInfo.setFlowCode(paystatus+"");
		PayedInfo.setRemarks(remarks);
		payedinfoDao.save(PayedInfo);
		//System.out.print("============================================["+shouldpay);
		return "ok";
	}
	
	

}

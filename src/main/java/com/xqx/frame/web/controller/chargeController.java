package com.xqx.frame.web.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.PlayQueryVO;


import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.Periodic;

import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;

import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TPayedInfo;
import com.xqx.frame.model.TUser;

import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.ChargeItemService;
import com.xqx.frame.service.ChildrenService;
import com.xqx.frame.service.FileService;
import com.xqx.frame.service.GradeService;
import com.xqx.frame.service.TPayedInfoService;

@Controller
@RequestMapping("/charge")
public class chargeController {
	private static final String Payetype = null;
	@Autowired
	ChildrenService childrenService;
	@Autowired
	TchargeItemDao chargeItemDao;
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	TPayedInfoDao payedinfoDao;
	@Autowired
	TPayedInfoService payedinfoservice;
	@Autowired
	TClasseDao classesService;
	@Autowired
	FileService fileservice;
	@Autowired
    GradeService gradeService;
	@Autowired
	ChargeItemService chargeItemService;
	
	
	
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


	
	@RequestMapping(value = "/chargelist")
    public String chargelist(Model m,
				//SessionStatus sessionStatus,
				@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
	
		
		String name = request.getParameter("childName");
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();

		
		Page<TPayedInfo> list = payedinfoservice.findAll(name,p);
		m.addAttribute("classe", classe);
		m.addAttribute("grade", grade);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");

		return "/charge/list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findChildHistory", method = RequestMethod.GET)
	public Object findChildHistory(HttpServletRequest request){
		Long childId = Long.valueOf(request.getParameter("childId"));
		
		System.out.print("==========================");
		List<PlayQueryVO> infolist = payedinfoservice.getTPayedInfo(10, childId);

		
		return new PageQueryResult<>(infolist);
		

	}

	
	
	
	
	
	/**
	 * 删除收费记录
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException 
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/del")
	public String deletePayedinfo(@PathVariable Long id, Model m) throws ParameterCheckException {
		payedinfoservice.deleteTPayedInfo(id);
		return "ok";
	}
	
	
	
	
	

	@ResponseBody
	@RequestMapping(value = "/addchargepay")
	
	public String addChargePay(HttpServletRequest request){
		//payedinfoDao.save(choose);charge-real-pay
		TUser user = (TUser) SecurityUtil.getCurrentUser();
		Date now = new Date();
		
		BigDecimal shouldpay = new BigDecimal(request.getParameter("charge-should-pay"));
		BigDecimal chargerealpay = new BigDecimal(request.getParameter("charge-real-pay"));
		BigDecimal chargereturn = new BigDecimal(request.getParameter("charge-return"));
		Long childId = Long.valueOf(request.getParameter("childId"));

		
		String paytype = request.getParameter("paytype");

		Payetyped paytypa =Payetyped.valueOf(paytype);
		
	
		String paystatus = request.getParameter("payStatus");

		String chargConnection = request.getParameter("chargConnection");
		//凌晨修改的代码
		List<TChargeItem> chargeitem = new ArrayList<TChargeItem>();
		if(!"".equals(chargConnection)&&chargConnection!=null){
			String[] ss = chargConnection.split(",");
		//Set<TChargeItem> trade = new HashSet<TChargeItem>();
	
		for(int i=0;i<ss.length;i++){
			
			chargeitem.add(chargeItemService.findChargeitemById(Long.valueOf(ss[i])));
			
			
		}
		}
		String chargConnectiontext=chargeitem.toString();
		
		String flowCode = request.getParameter("flowCode");
		String remarks = request.getParameter("bz");
		
		TChildren children=childrenDao.findOne(childId);
		TPayedInfo PayedInfo=new  TPayedInfo();
		PayedInfo.setChargeshouldpay(shouldpay);
		PayedInfo.setChargerealpay(chargerealpay);
		PayedInfo.setUser(user);
		PayedInfo.setPayableDsate(now);
		PayedInfo.setTimeb(now);
		PayedInfo.setFlowCode(flowCode);
		PayedInfo.setChargConnection(chargConnection);
		PayedInfo.setChargereturn(chargereturn);
		PayedInfo.setChildren(children);
		//PayedInfo.setPaytype(paytypa.CARD);
		PayedInfo.setPaytype(paytypa);
		PayedInfo.setFlowCode(paystatus+"");
		PayedInfo.setRemarks(remarks);
		PayedInfo.setChargConnectiontext(chargConnectiontext);
		payedinfoDao.save(PayedInfo);
		//System.out.print("============================================["+shouldpay);
		return "ok";
	}
	
	

}

package com.xqx.frame.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TEmporaryTicketDao;
import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.dao.TIcketTemplateDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.Periodic;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TEmporaryTicket;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TIcketTemplate;
import com.xqx.frame.model.TPayedInfo;
import com.xqx.frame.model.TUser;

import com.xqx.frame.model.TicketLevel;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.ChargeItemService;
import com.xqx.frame.service.ChildrenService;
import com.xqx.frame.service.GradeService;
import com.xqx.frame.service.TEmporaryTicketService;


import net.sf.json.JSONObject;


@Controller
@RequestMapping("/ticket")
public class TEmporaryTicketController {

	@Autowired
	ChargeItemService chargeItemService;
	
	@Autowired
	GradeService gradeService;
	@Autowired
	TGradeDao gradeDao;
	@Autowired
	TClasseDao classeDao;
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	TEmporaryTicketService temporaryTicketService;
	@Autowired
	ChildrenService childrenService;
	@Autowired
	TIcketTemplateDao ticketTemplateDao;
	@Autowired
	TPayedInfoDao payedinfosDao;
	@Autowired
	TEmporaryTicketDao temporaryTicketDao;
	@Autowired
	TPayedInfoDao payedinfoDao;
	
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newChargeItem(Model m){
		//String todo= request.getParameter("todo");
		TChargeItem chargeitem = new TChargeItem();
		m.addAttribute("chargeitem",chargeitem);
		//m.addAttribute("todo",todo);
		m.addAttribute("Periodic",Periodic.values());
		m.addAttribute("TicketLevel", TicketLevel.values());
		return "/chargeitem/createOrUpdate";

    }
	
	@ResponseBody
	@RequestMapping(value = "/chargeaddtemporary",method=RequestMethod.POST)
	public Object chargeaddtemporary(Model m,HttpServletRequest request) throws Exception{
		JSONObject jsonObj = new JSONObject();
		String tempname = request.getParameter("tempName");
		String[] chargeNameArray = request.getParameterValues("chargeName");
		String[] chargeAmountArray = request.getParameterValues("chargeAmount");

	
		TIcketTemplate TIcketTemplate=new TIcketTemplate();
		TIcketTemplate.setTempname(tempname);
		 ticketTemplateDao.save(TIcketTemplate);
			List<TEmporaryTicket> Ticketlist = new ArrayList<TEmporaryTicket>();
      	Set<TEmporaryTicket> Ticket = new HashSet<TEmporaryTicket>();
		if(chargeNameArray!=null&&chargeNameArray.length>0){
			for(int i = 0;i<chargeNameArray.length;i++){
				TEmporaryTicket owner = new TEmporaryTicket();
			 owner.setChargeName(chargeNameArray[i]);
			 BigDecimal chargerealpay = new BigDecimal(chargeAmountArray[i]);
			 owner.setChargeAmount(chargerealpay);
			 owner.setTemplates(TIcketTemplate);
			 Ticket.add(owner);
			
		      }
			 temporaryTicketDao.save(Ticket);
			// Ticketlist.addAll(Ticket);
			// jsonObj.put("ticket", Ticketlist);
			// jsonObj.put("res", "1");
			// jsonObj.put("temp", TIcketTemplate.getId());
		   // return jsonObj.toString();
			 return TIcketTemplate; 
		}else {
			 return null; 
			 
		}
		
	 }
	
	@ResponseBody
	@RequestMapping(value = "/selecttemporarychargeinfo",method=RequestMethod.POST)
	public Object selectTemporaryChargeInfo(Model m,HttpServletRequest request) throws Exception{
		String pid = request.getParameter("pid");
		//List<TEmploye> list = employeDao.findAll();
		List<TEmporaryTicket> temporaryTicket=temporaryTicketService.findTEmporaryTicketByTemplateid(Long.valueOf(pid));
		if(temporaryTicket!=null&&temporaryTicket.size()>0){
		    return temporaryTicket;
		}else {
			 return null; 
			 
		}
	 }
	
	

	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/deletetemporarypid",method=RequestMethod.POST)
	public Object deleteTemporaryChargeInfoByPid(Model m,HttpServletRequest request) throws ParameterCheckException {
		JSONObject jsonObj = new JSONObject();
		String pid = request.getParameter("pid");
		//List<TEmploye> list = employeDao.findAll();
		
		temporaryTicketService.deleteTEmporaryTicketBytemplateid(Long.valueOf(pid));
	
		jsonObj.put("res", "1");
		
		return jsonObj;
		
	 }
	

	/*
	 * cs收据保持
	 * 
	 */

	@ResponseBody 
	@RequestMapping(value = "/addchargetemporarycs")
	public String newClass(Model m,HttpServletRequest request) {

        TUser user = (TUser) SecurityUtil.getCurrentUser();
      
        Date now = new Date();
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		String childName = request.getParameter("childName");
		String childSex = request.getParameter("childSex");
		String classe = request.getParameter("classe");

	
		return "ok";
		//String tempname = request.getParameter("tempName");
		//String[] chargeNameArray = request.getParameterValues("chargeName");
		//String[] chargeAmountArray = request.getParameterValues("chargeAmount");

		
/*		String childName = request.getParameter("childName");
		String childSex = request.getParameter("childSex");
		String childBirthday = request.getParameter("childBirthday");
		String childInGartenDate = request.getParameter("childInGartenDate");
		Date childBirthdays = null,childInGartenDates= null;
		try {
			childBirthdays = sdf.parse(childBirthday+" 00:00:00");
			childInGartenDates=sdf.parse(childInGartenDate+" 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String classe = request.getParameter("classe");
		String grade = request.getParameter("grade");
		TGrade grades=gradeDao.findOne(Long.valueOf(grade));
		TClasses classes=classeDao.findOne(Long.valueOf(classe));
		*/
		
		
	

		
		
		
		//BigDecimal shouldpay = new BigDecimal(request.getParameter("amount"));
		//BigDecimal chargerealpay = new BigDecimal(request.getParameter("payed"));
		//BigDecimal chargereturn = new BigDecimal(request.getParameter("charge-return"));
		//Long childId = Long.valueOf(request.getParameter("childId"));


		//String paytype = request.getParameter("paytype");

		//Payetyped paytypa =Payetyped.valueOf(paytype);
		//String paytypas=paytypa.toString();
		
		//String paystatus = request.getParameter("payStatus");
		//String remarks = request.getParameter("bz");
		
		//TIcketTemplate TIcketTemplate=new TIcketTemplate();
		//TIcketTemplate.setTempname(tempname);
		// ticketTemplateDao.save(TIcketTemplate);
			/*List<TEmporaryTicket> Ticketlist = new ArrayList<TEmporaryTicket>();
      	Set<TEmporaryTicket> Ticket = new HashSet<TEmporaryTicket>();
		
      	
      	if(chargeNameArray!=null&&chargeNameArray.length>0){
			for(int i = 0;i<chargeNameArray.length;i++){
				TEmporaryTicket owner = new TEmporaryTicket();
			 owner.setChargeName(chargeNameArray[i]);
			 BigDecimal chargerealpays = new BigDecimal(chargeAmountArray[i]);
			 owner.setChargeAmount(chargerealpays);
			 //owner.setTemplates(TIcketTemplate);
			 Ticket.add(owner);
			
		      }
			// temporaryTicketDao.save(Ticket);
			 Ticketlist.addAll(Ticket);
      	}*/
/*      	TChildren children=new TChildren();
      	children.setChildName(childName);
      	children.setChildInGartenDate(childInGartenDates);
      	children.setChildSex(childSex);
      	children.setClasse(classes);
      	children.setGrade(grades);
      	children.setChildBirthday(childBirthdays);*/
      	
      	
/*      	
		//TChildren childr=childrenDao.findOne(childId);
		TPayedInfo payedInfo=new TPayedInfo(paytypas);
		payedInfo.setChargeshouldpay(shouldpay);
		payedInfo.setChargerealpay(chargerealpay);
		payedInfo.setUser(user);
		payedInfo.setPayableDsate(now);
		payedInfo.setTimeb(now);
		//PayedInfo.setFlowCode(flowCode);
		//PayedInfo.setChargConnection(chargConnection);
		payedInfo.setChargereturn(chargereturn);
		payedInfo.setChildren(childrenDao.save(children));
		//PayedInfo.setPaytype(paytypa.CARD);
		//PayedInfo.setPaytype(paytypas);
		payedInfo.setFlowCode(paystatus+"");
		payedInfo.setRemarks(remarks);
		payedInfo.setChargConnectiontext(Ticketlist.toString());

		
		payedinfoDao.save(payedInfo);
		*/

		
		
		
		}

	
	
	/*
	 * 收据保持
	 * 
	 */

	@ResponseBody 
	@RequestMapping(value = "/addchargetemporary")
	public String addchargetemporary(HttpServletRequest request) throws ParameterCheckException {

        TUser user = (TUser) SecurityUtil.getCurrentUser();
      
        Date now = new Date();
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		//String tempname = request.getParameter("tempName");
		String[] chargeNameArray = request.getParameterValues("chargeName");
		String[] chargeAmountArray = request.getParameterValues("chargeAmount");

		
		String childName = request.getParameter("childName");
		String childSex = request.getParameter("childSex");
		String childBirthday = request.getParameter("childBirthday");
		String childInGartenDate = request.getParameter("childInGartenDate");
		Date childBirthdays = null,childInGartenDates= null;
		try {
			childBirthdays = sdf.parse(childBirthday+" 00:00:00");
			childInGartenDates=sdf.parse(childInGartenDate+" 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String classe = request.getParameter("classe");
		String grade = request.getParameter("grade");
		TGrade grades=gradeDao.findOne(Long.valueOf(grade));
		TClasses classes=classeDao.findOne(Long.valueOf(classe));
		
		
		
	

		
		
		
		BigDecimal shouldpay = new BigDecimal(request.getParameter("amount"));
		BigDecimal chargerealpay = new BigDecimal(request.getParameter("payed"));
		BigDecimal chargereturn = new BigDecimal(request.getParameter("charge-return"));
		//Long childId = Long.valueOf(request.getParameter("childId"));


		String paytype = request.getParameter("paytype");

		Payetyped paytypa =Payetyped.valueOf(paytype);
		String paytypas=paytypa.toString();
		
		String paystatus = request.getParameter("payStatus");
		String remarks = request.getParameter("bz");
		
		//TIcketTemplate TIcketTemplate=new TIcketTemplate();
		//TIcketTemplate.setTempname(tempname);
		// ticketTemplateDao.save(TIcketTemplate);
			List<TEmporaryTicket> Ticketlist = new ArrayList<TEmporaryTicket>();
      	Set<TEmporaryTicket> Ticket = new HashSet<TEmporaryTicket>();
		
      	
      	if(chargeNameArray!=null&&chargeNameArray.length>0){
			for(int i = 0;i<chargeNameArray.length;i++){
				TEmporaryTicket owner = new TEmporaryTicket();
			 owner.setChargeName(chargeNameArray[i]);
			 BigDecimal chargerealpays = new BigDecimal(chargeAmountArray[i]);
			 owner.setChargeAmount(chargerealpays);
			 //owner.setTemplates(TIcketTemplate);
			 Ticket.add(owner);
			
		      }
			// temporaryTicketDao.save(Ticket);
			 Ticketlist.addAll(Ticket);
      	}
      	TChildren children=new TChildren();
      	children.setChildName(childName);
      	children.setChildInGartenDate(childInGartenDates);
      	children.setChildSex(childSex);
      	children.setClasse(classes);
      	children.setGrade(grades);
      	children.setChildBirthday(childBirthdays);
      	
      	
      	
		//TChildren childr=childrenDao.findOne(childId);
		TPayedInfo payedInfo=new TPayedInfo(paytypas);
		payedInfo.setChargeshouldpay(shouldpay);
		payedInfo.setChargerealpay(chargerealpay);
		payedInfo.setUser(user);
		payedInfo.setPayableDsate(now);
		payedInfo.setTimeb(now);
		//PayedInfo.setFlowCode(flowCode);
		//PayedInfo.setChargConnection(chargConnection);
		payedInfo.setChargereturn(chargereturn);
		payedInfo.setChildren(childrenDao.save(children));
		//PayedInfo.setPaytype(paytypa.CARD);
		//PayedInfo.setPaytype(paytypas);
		payedInfo.setFlowCode(paystatus+"");
		payedInfo.setRemarks(remarks);
		payedInfo.setChargConnectiontext(Ticketlist.toString());

		
		payedinfoDao.save(payedInfo);
		

		return "ok";
		
		
		}


	
	

	
}
package com.xqx.frame.web.controller;


import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.PlayDeaFinfo;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.*;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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



	
	/*
	 * 收费统计按天统计
	 */
	
	@RequestMapping(value = "/statistics")
    public String chargeStatistics(Model m,@RequestParam(defaultValue="2019-04-17") String date,HttpServletRequest request) throws ParseException{
		
		
		String paytype = request.getParameter("paytype");
	
		
		Payetyped paytypas = Payetyped.get(paytype);
		
	
		
		//Date date1 = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(Validator.isNull(startDate)?"2000-01-01":startDate).toDate();
		//Date date2 = Validator.isNull(endDate) ? (new DateTime().plusYears(10).toDate()):DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(endDate).plusDays(1).toDate();
		
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			
			Date stardate=sdf.parse(date+" 00:00:00");
            Date enddate=sdf.parse(date+" 23:59:59");
               
		
            
		List<TPayedInfo>  lists = payedinfoservice.findAll(paytypas,stardate,enddate);
		List<String> counts=payedinfoDao.payedInfoStaticonut(paytypas,stardate,enddate);
        Long sumt=payedinfoDao.payedInfoStatisum(paytypas,stardate,enddate);
    
        long numberOfEnemies = sumt!=null?sumt:0;
        
		Payetyped[] Payed = Payetyped.values();
		m.addAttribute("data", lists);
		
		m.addAttribute("counts", counts.size());
		m.addAttribute("sumt", numberOfEnemies);
		m.addAttribute("Payed", Payed);
		return "/charge/statistics";
	}
	
	
	/*
	 * 
	 * 查询选中学生缴费记录
	 */

	
	
	@RequestMapping(value = "/historyinfo")
    public String historyinfo(Model m,HttpServletRequest request){
	    String id = request.getParameter("id");
		List<TPayedInfo>  lists=payedinfoDao.payedInfoStatiConut(Long.valueOf(id));
		m.addAttribute("data", lists);
		return "/charge/selectchildhistoryinfo";
	}
	
	
	



/*
	@RequestMapping(value = "/{id}/selectchildhistoryinfo")
    public String selectChildHistoryInfo(Model m,@PathVariable Long id,HttpServletRequest request){


		//List<TPayedInfo>  lists=payedinfoDao.payedInfoStatiConut(id);
		//m.addAttribute("data", lists);

		return "/charge/selectchildhistoryinfo";
	}
	*/

	/*
	 * 缴费信息列表
	 */
	@RequestMapping(value = "/chargelist")
    public String chargelist(Model m,
				//SessionStatus sessionStatus,
				@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("childName");
		String beginTime = request.getParameter("beginTime");
		m.addAttribute("beginTime",beginTime);
		String endTime = request.getParameter("endTime");
		m.addAttribute("endTime", endTime);
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
		Page<TPayedInfo> list = payedinfoservice.findAll(name,beginTime,endTime,p);
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

	
	
	@ResponseBody
	@RequestMapping(value = "/findHistoryprints", method = RequestMethod.POST)
	public PlayDeaFinfo findHistoryprints(HttpServletRequest request){
		Long cid = Long.valueOf(request.getParameter("cid"));
		
		System.out.print("==========================");
		PlayDeaFinfo  info = payedinfoservice.getTPayeddeafultInfo(cid);

		
		return  info;
		

	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String deletePayed(String id) throws ParameterCheckException {
		String Stats= "ok";
		 id = id.trim();
	        String[] arr = id.trim().split(",");
	        for (String item : arr){
	        	 if(!StringUtils.isEmpty(item)){
	                Long baId = Long.parseLong(item);
	                payedinfoservice.deleteTPayedInfo(baId);
	                Stats= "ok";
	            }else {
	            	Stats= "no";
	            }
	        }
	      return Stats;
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
		String paytypas=paytypa.toString();
		
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
		TPayedInfo PayedInfo=new TPayedInfo(paytypas);
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
		//PayedInfo.setPaytype(paytypas);
		PayedInfo.setFlowCode(paystatus+"");
		PayedInfo.setRemarks(remarks);
		PayedInfo.setChargConnectiontext(chargConnectiontext);
		payedinfoDao.save(PayedInfo);
		//System.out.print("============================================["+shouldpay);
		return "ok";
	}
	
	

}

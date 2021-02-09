package com.xqx.frame.mobile.controller.admin;

import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.Periodic;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TicketLevel;
import com.xqx.frame.service.ChargeItemService;
import com.xqx.frame.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/m/chargeitem")
public class MchargeItemController {

	@Autowired
	ChargeItemService chargeItemService;
	@Autowired
	TchargeItemDao chargeItemDao;
	@Autowired
	GradeService gradeService;
	
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
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newChargeItem(Model m,@ModelAttribute("chargeitem") TChargeItem chargeitem,BindingResult bind, SessionStatus status) throws ParameterCheckException, IOException{

		String saveMsg = chargeItemService.saveChargeItem(chargeitem);
	
		if("exist".equals(saveMsg)) {	
			return "/chargeitem/createOrUpdate";
		}else{
			return "redirect:/chargeitem/list";
		}
	 }

	@ResponseBody
	@RequestMapping(value = "/{id}/gradeitem", method = RequestMethod.GET)
	public List getchargeitem(@PathVariable Long id, Model m) {
		
		
		System.out.print("==============================================================");
		String Chargeitem=gradeService.findGradeById(id).getChargeitem();
		
		List<TChargeItem> roleList = new ArrayList<TChargeItem>();
		if(!"".equals(Chargeitem)&&Chargeitem!=null){
			String[] ss = Chargeitem.split(",");
		//Set<TChargeItem> trade = new HashSet<TChargeItem>();
	
		for(int i=0;i<ss.length;i++){
			
			roleList.add(chargeItemService.findChargeitemById(Long.valueOf(ss[i])));
			
			
		}
		}
		
	
		
		return roleList;
	}

	/*
	 * 全部收费项目
	 */
	@ResponseBody
	@RequestMapping(value = "/chargeList")
	public QueryResult chargeList(Model m,@ModelAttribute("chargeitem") TChargeItem chargeitem,BindingResult bind, SessionStatus status) throws ParameterCheckException, IOException{

		List<TChargeItem> Itemlist=chargeItemDao.findAll();
		return new QueryResult<>(Itemlist);
	}
	
	@RequestMapping("/list")
	public String list(Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("ItemName");
		String mtop = request.getParameter("mtop");

		m.addAttribute("name", name);
		m.addAttribute("mtop", mtop);
		Page<TChargeItem> list = chargeItemService.findAll(name, p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
	
		if(mtop!=null) {
			List<TGrade> lists=gradeService.findGradeByGradeName(mtop);
			m.addAttribute("datas", lists);
		}else {
			List<TGrade> lists=gradeService.findAll();
			m.addAttribute("datas", lists);
			
		}
		
		
		
		m.addAttribute("method", "get");
		return "/chargeitem/list";
	}
	
	/**
	 * 初始化修改专家信息
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String initEdit(@PathVariable Long id, Model m) {
		TChargeItem chargeitem = chargeItemService.findChargeitemById(id);
	
		m.addAttribute("Periodic",Periodic.values());
		m.addAttribute("TicketLevel", TicketLevel.values());
		m.addAttribute("chargeitem", chargeitem);
		m.addAttribute("msg", "edit");
		return "chargeitem/createOrUpdate";
	}

	/**
	 * 处理修改专家信息
	 * 
	 * @param expert
	 * @param bind
	 * @param status
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editChargeItem(@ModelAttribute("chargeitem") TChargeItem chargeitem,
			BindingResult bind, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "chargeitem/createOrUpdate";
		}
		chargeItemService.saveChargeItem(chargeitem);
		m.addAttribute("classes", chargeitem);
		m.addAttribute("msg", "editOk");
		return "chargeitem/createOrUpdate";
	}

	/**
	 * 删除专家
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException 
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/del")
	public String deleteChargeItem(@PathVariable Long id, Model m) throws ParameterCheckException {
		chargeItemService.deleteChargeitem(id);
		return "ok";
	}
	
	/**
	 * 关联收费项
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException 
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/conne",method = RequestMethod.POST)
	public List<TChargeItem> connectedChargeItem(@PathVariable Long id, Model m) throws ParameterCheckException {
		String chargeitem=gradeService.findGradeById(id).getChargeitem();
		
		//TChargeItem chargeitems = null;
		List<TChargeItem> roleList = new ArrayList<TChargeItem>();
		if(!"".equals(chargeitem)&&chargeitem!=null){
			String[] ss = chargeitem.split(",");
		//Set<TChargeItem> trade = new HashSet<TChargeItem>();
	
		for(int i=0;i<ss.length;i++){
			
			roleList.add(chargeItemService.findChargeitemById(Long.valueOf(ss[i])));
			
			
		}
		}
		
		
		return roleList;
	}
	
	
	

	
}
package com.xqx.frame.mobile.controller.admin;

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TIcketTemplateDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.query.childrenQuery;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/m/children")
public class MchildrenController {
	

	@Autowired
	ChildrenService childrenService;

	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	ClassesService classesService;
	@Autowired
	FileService fileservice;
	@Autowired
	GradeService gradeService;

	@Autowired
	TchargeItemDao chargeItemDao;
	@Autowired
	ChargeItemService chargeItemService;

	@Autowired
	TIcketTemplateDao ticketTemplateDao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 新增专家

	 * @return
	 * @throws ParameterCheckException
	 * @throws IOException
	 */
@ResponseBody
@RequestMapping(value = "/new", method = RequestMethod.POST, consumes="application/json", produces="application/json")
 public JSONObject addUser(@RequestBody JSONObject body) throws ParameterCheckException {

	TChildren children = (TChildren) JSONObject.toBean(body,TChildren.class);
	JSONObject json = JSONObject.fromObject(body);
	System.out.print(json.getString("grade")+"==================================================="+children.getGrade().getId());

	TClasses classes=classesService.findClassesById(Long.valueOf(json.getString("classe")));
	TGrade grate=gradeService.findGradeById(Long.valueOf(json.getString("grade")));
	children.setGrade(grate);
	children.setClasse(classes);

	System.out.print(json.getString("grade")+"==================================================="+children.getGrade().getId());
	/*  return "添加失败！";*/
	String saveMsg = childrenService.saveChildren(children);
	JSONObject res = new JSONObject();
	if ("exist".equals(saveMsg)) {
		res.put("status",0);
		res.put("msg","添加失败");
	} else {
		res.put("status",1);
		res.put("msg","添加成功");
	}
	return res;
}


    /**
     * 修改学生信息
     *
     * @param children
     * @param bind
     * @param status
     * @return
     * @throws ParameterCheckException
     */


//    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes="application/json", produces="application/json")
//    public JSONObject addUser(@RequestBody JSONObject body) throws ParameterCheckException {


    @ResponseBody
    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.POST ,  consumes="application/json", produces="application/json")
    public String editChildren(@PathVariable Long id,@RequestBody JSONObject body)
			throws ParameterCheckException {
		TChildren children = (TChildren) JSONObject.toBean(body,TChildren.class);
		System.out.println("============"+children);


		JSONObject json = JSONObject.fromObject(body);

		String obj = json.getString("gropObject");



		JSONObject jsonObject = JSONObject.fromObject(obj);

		 String gradeId = jsonObject.getString("gradeId");
		 String classeId = jsonObject.getString("classeId");
		System.out.println("============="+Long.valueOf(gradeId));


		TGrade grate=gradeService.findGradeById(Integer.valueOf(gradeId));
		TClasses classes=classesService.findClassesById(Integer.valueOf(classeId));
		children.setGrade(grate);
		children.setClasse(classes);

		String saveMsg = childrenService.saveChildren(children);
//        JSONObject res = new JSONObject();
//        if ("exist".equals(saveMsg)) {
//            res.put("status",0);
//            res.put("msg","添加失败");
//        } else {
//            res.put("status",1);
//            res.put("msg","添加成功");
//        }
        return saveMsg;
    }




@ResponseBody
@RequestMapping(value = "/upfile",method=RequestMethod.POST)
public JSONObject weixinupice(@RequestParam("photoDir") MultipartFile photoDir,HttpServletRequest request) throws  ParameterCheckException,IOException{ 
	//System.out.println("<br/>============="+fileservice.upphote(photoDir)+"==============================");
	JSONObject jsonObj = new JSONObject();
	  String usersp=request.getParameter("user");
	jsonObj.put("photoDir",fileservice.upphote(photoDir));
	jsonObj.put("users",usersp);
	return jsonObj;
}

@ResponseBody
@RequestMapping("/list")
public Object querychildList(Integer page, Integer limit,HttpServletRequest request,@ModelAttribute childrenQuery query){
		Page<TChildren> list = childrenService.findAllt(query,page - 1,limit);
	Principal principal = request.getUserPrincipal();
	SecurityUtil.getCurrentUserDetials();

	//System.out.println("===1==="+ principal.getName()+"===");
		return new PageQueryResult<>(list);
	}



@ResponseBody
@RequestMapping(value = "/read/{id}")
public Object readChildren(@PathVariable Long id, Model m) throws ParameterCheckException, IOException, IllegalAccessException {
		TChildren children = childrenService.findChildrenById(id);
		String chargConnection = children.getChargConnection();
		List<TChargeItem> chargeitem = new ArrayList<TChargeItem>();
		if(!"".equals(chargConnection)&&chargConnection!=null){
			String[] ss = chargConnection.split(",");
			for(int i=0;i<ss.length;i++){
				chargeitem.add(chargeItemService.findChargeitemById(Long.valueOf(ss[i])));
			}
		}
	children.setChargList(chargeitem);

	           // JSONObject jsonObj = new JSONObject();








//	for (Field field : e.getClass().getDeclaredFields()) {
//		field.setAccessible(true);
//		try {
//			System.out.println(field.getName() + ":" + field.get(children) );
//		} catch (IllegalAccessException e1) {
//			e1.printStackTrace();
//		}
//	}


//
//	jsonObj.put("children",children);8




	//JSONArray allSongListArray = JSONArray.fromObject(jsonObj);

//System.out.println(jsonObj);

				//JSONArray ss=JSONArray.fromObject(chargeitem);
				//JSONArray child=JSONArray.fromObject(children);

				//jsonObj.put("chargeitem",ss);
				//jsonObj.put("children",chargeitem);
				//jsonObj.put("sexType",SexType.values());

//		m.addAttribute("chargeitem", chargeitem);
//		m.addAttribute("children", children);
//		m.addAttribute("sexType", SexType.values());

	    return children;
	}



@ResponseBody
@RequestMapping(value ="/childbirthday",method=RequestMethod.POST)
public Object childBirthDay(Model m,
										 //SessionStatus sessionStatus,
										 @PageableDefault(page = 0, size = 10,direction = Sort.Direction.DESC) Pageable p,
								SessionStatus status,
								HttpServletRequest request)
			throws ParameterCheckException {

		String gid = request.getParameter("selectDate");
		//Page<TChildren> list = childrenService.findAllt(query,page - 1,limit);
		Page<TChildren> list = childrenService.findAll(gid,p);
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
System.out.println("-------------------333"+list);
		return new PageQueryResult<>(list);
	}

/*@ResponseBody
@RequestMapping(value = "/new",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
public String insertChildren(@RequestParam Map<String, String> params,@RequestBody JSONObject children) throws ParameterCheckException{ 

		System.out.print("sdfsdfsdf==================================================="+children.getChildName());
		String saveMsg = childrenService.saveChildren(children);
		if ("exist".equals(saveMsg)) {
			return "添加失败！";
		} else {
			return "添加成功！";

		}
	}*/
}

package com.xqx.frame.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.PageResult;

import com.xqx.frame.dao.PropertyDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TProperty;
import com.xqx.frame.service.PropertyService;


/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:/spring/root-context.xml"})*/
@Controller
@RequestMapping("/property")
public class PropertyController {
	
	@Autowired
	PropertyService propertyService;
	
	@Autowired
	PropertyDao Property;
	

	
	/**
	 * 初始化新增专家
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newProperty(Model m,HttpServletRequest request){
		
		TProperty property = new TProperty();
		if(request.getParameter("fid") !=""&&request.getParameter("fid")!=null&&!request.getParameter("fid").equals("")) {
			
			TProperty propertys = propertyService.getById(Long.valueOf(request.getParameter("fid")));
				property.setfId(Long.parseLong(request.getParameter("fid")));
				property.setfName(propertys.getfName());
		}
		m.addAttribute("property", property);
		m.addAttribute("msg", "new");
		return "/property/createOrUpdate";
	}

	/**
	 * @throws IOException 
	 * 新增专家
	 * @param m
	 * @param expert
	 * @return
	 * @throws ParameterCheckException
	 * @throws  
	 */
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newpropertys(Model m,@ModelAttribute("property") TProperty property,BindingResult bind, SessionStatus status) throws ParameterCheckException, IOException{
		//expert.setAvailability(1);
		/*PGJG[] pgjgs = PGJG.values();
		m.addAttribute("pgjgs", pgjgs);*/
		//fileService.webUpload(multipartFiles);
	
		System.out.println("=======================");
		String saveMsg = propertyService.save(property);
		m.addAttribute("property", property);
	
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/property/createOrUpdate";
		}else{
			return "redirect:/property/list";
		}
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/lists")
	public Object lists(Model m,HttpServletRequest request){
		String fname = StringUtils.trim(request.getParameter("fValue"));
		m.addAttribute("fname", fname);
		List<TProperty> roleList =Property.findAll();
		 return new PageQueryResult<>(roleList);
		
		
		
		
		
		
		
		
	}

	
	@ResponseBody
	@RequestMapping("/lis")
	public PageResult lis(Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String fname = StringUtils.trim(request.getParameter("fValue"));
		m.addAttribute("fname", fname);
		Page<TProperty> lists = propertyService.findAll(fname, p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", lists.getTotalElements());

		PageResult result = new PageResult();
	

		result.setiTotalRecords(p.getPageSize());
		result.setiTotalDisplayRecords(lists.getTotalElements());
		result.setAaData(JSONArray.parseArray(JSON.toJSONString(lists,SerializerFeature.WriteMapNullValue)));
		result.setsEcho(p.getPageNumber()+ 1);
		return result;
	}
	
	
	@RequestMapping("/list")
	public String list(Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String fname = StringUtils.trim(request.getParameter("fValue"));
		m.addAttribute("fname", fname);
		
		Page<TProperty> list = propertyService.findAll(fname, p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		return "/property/list";
	}
	
	
	@RequestMapping(value = "/{id}/list")
	public String fidlists(@PathVariable String id, Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request) {

		String fname="";
		if(StringUtils.isNotEmpty(request.getParameter("fValue"))) {
			fname = StringUtils.trim(request.getParameter("fValue"));
			
		}else {
			 fname = StringUtils.trim(id);
			 
		}
		
		
		m.addAttribute("fname", fname);
		
		Page<TProperty> list = propertyService.findAll(fname, p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		
		
		
		
		
		return "/property/fidlist";
	}
	
	
	
	
	
	/*public String list(Model m,HttpServletRequest request){
		
		return "/expert/list";
	}*/
	/**
	 * 初始化修改专家信息
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String initEdit(@PathVariable Long id, Model m) {
		TProperty property = propertyService.getById(id);
		m.addAttribute("property", property);
		m.addAttribute("msg", "edit");
		return "property/createOrUpdate";
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
	public String editGrade(@ModelAttribute("property") TProperty property,
			BindingResult bind, Model m)
			throws ParameterCheckException,IOException {
		//if (bind.hasErrors()) {
		//	return "grade/createOrUpdate";
		//}

		propertyService.save(property);
		m.addAttribute("property", property);
		m.addAttribute("msg", "editOk");
	
		
		
		return "property/createOrUpdate";
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
	public String deleteTGrade(@PathVariable Long id, Model m) throws ParameterCheckException {
		propertyService.delete(id);
		return "ok";
	}

}

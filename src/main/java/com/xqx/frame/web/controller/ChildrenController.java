package com.xqx.frame.web.controller;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TIcketTemplateDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.ChildrenQueryVO;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.model.*;
import com.xqx.frame.model.query.childrenQuery;
import com.xqx.frame.service.ChargeItemService;
import com.xqx.frame.service.ChildrenService;
import com.xqx.frame.service.FileService;
import com.xqx.frame.service.GradeService;
import com.xqx.frame.util.PubUtil;
import com.xqx.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/children")
public class ChildrenController {
	@Autowired
	ChildrenService childrenService;
	
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	TClasseDao classesService;
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		// binder.registerCustomEditor(int.class, new
		// CustomNumberEditor(int.class, true));
		// binder.registerCustomEditor(int.class, new IntegerEditor());
		// binder.registerCustomEditor(long.class, new
		// CustomNumberEditor(long.class, true));
		// binder.registerCustomEditor(long.class, new LongEditor());
		// binder.registerCustomEditor(double.class, new DoubleEditor());
		// binder.registerCustomEditor(float.class, new FloatEditor());
	}

	
	
	
	
	
	
	/**
	 * 初始化新增专家
	 * @param m
	 * @return
	 */

	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newChildren(Model m,HttpServletRequest request){
		
		TChildren children = new TChildren();
		Object topId=request.getSession().getAttribute("topId");
		if (!Validator.isNull(topId)) {
			long topIds=Long.valueOf(topId.toString());
			TKindergarten kindergarten=new TKindergarten();
			kindergarten.setId(topIds);
			children.setKindergarten(kindergarten);
		}
		children.setChildSchoolId(childrenService.getSystemSequence("contractSequence"));
		List<TGrade> grade=gradeService.findAll();
		List<TClasses> classe=classesService.findAll();
		List<TChargeItem> Itemlist=chargeItemDao.findAll();
		m.addAttribute("Itemlist", Itemlist);
		m.addAttribute("children", children);
		m.addAttribute("grade", grade);
		m.addAttribute("classe", classe);
	
		m.addAttribute("msg", "new");
		return "/children/createOrUpdate";
	}

	/**
	 * 新增学生
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 * @throws IOException 
	 */
	
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newClass(Model m,
						   @ModelAttribute("children") TChildren children,BindingResult bind, SessionStatus status,@RequestParam("photoDir") MultipartFile multipartFile) throws ParameterCheckException,IOException{


		children.setPhotoDir(fileservice.upphote(multipartFile));
		String chargConnection=children.getChargConnection();
		if("".equals(chargConnection)&&chargConnection==null){
			children.setChargConnection(children.getClasse().getChargeitem());
		}
		String saveMsg = childrenService.saveChildren(children);
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/children/createOrUpdate";
		}else{
			return "redirect:/children/list";
		}
	}
	
	
	/**
	 * 学生生日查询
	 * @param m
	 * @return
	 * 
	 */

	@RequestMapping("/childbirthday")
	public String childBirthDay(Model m,
			//SessionStatus sessionStatus,
			@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
	
		String gid = request.getParameter("selectDate");
		Page<TChildren> list = childrenService.findAll(gid,p);
		System.out.println("-------------------55---"+list);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("gid", gid);
		return "/children/childBirthlist";
	}
	
	
	@ResponseBody
	@RequestMapping("/wxlist")
	public Object wxlist(Integer page, Integer limit, childrenQuery query){
		//String name = request.getParameter("employeName");
		//System.out.println("========================="+p.getPageNumber());
   
		Page<TChildren> list = childrenService.findAllt(query,page - 1,limit);
		//System.out.println("========================="+list.getContent().get(0).tsting());
		return new PageQueryResult<>(list);

	}


	/**
	 * 学生详细列表
	 * @param m
	 * @return
	 * 
	 */
	@RequestMapping("/list")
	public String childrenlist(Model m,
			//SessionStatus sessionStatus,
			@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("childName");
		String cid = request.getParameter("cid");
		String gid = request.getParameter("gid");
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
		List<TIcketTemplate> ticketTemp= ticketTemplateDao.findAll();
		
	System.out.println("===================="+cid+"------"+gid);
		//m.addAttribute("advLinks", user.getId());
		//sessionStatus.setComplete();
	TChildren children = new TChildren();
	   
		Page<TChildren> list = childrenService.findAll(name,cid,gid,p);
		m.addAttribute("classe", classe);
		m.addAttribute("grade", grade);
		 m.addAttribute("ticketTemp", ticketTemp);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("children", children);
		m.addAttribute("method", "get");
		return "/children/list";
	}

	

	/**
	 * 老生选择添加收据列表
	 * @param m
	 * @return
	 * 
	 */
	@RequestMapping("/selectAllChildren")
	public String selectchildrenlist(Model m,HttpServletRequest request){
		String name = request.getParameter("searchName");
	   
		List<TChildren> list = childrenService.findChildrenByName(name);
		m.addAttribute("children", list);
	
		return "/children/sellist";
	}
	/**
	 * 输入学生姓名查询学生
	 * @param m
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("/queryChildren")
	public Object querychildrenlist(Model m,HttpServletRequest request){
		String name = request.getParameter("searchName");
	   
		List<TChildren> list = childrenService.findBychildrennamejson(name);

	
		return list;
	}
	
	/**
	 * 输入学生姓名查询学生
	 * @param m
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("/selectChildById")
	public ChildrenQueryVO selectChildById(Model m,HttpServletRequest request){
		String childId = request.getParameter("childId");
		ChildrenQueryVO list = childrenService.findChildrensById(Long.valueOf(childId));
		return list;
	}
	

	

	/**
	 * cs学生列表
	 * @param m
	 * @return
	 * 
	 */
	@RequestMapping("/listy")
	public String childrenlisty(Model m,
			//SessionStatus sessionStatus,
			@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("childName");
		String cid = request.getParameter("cid");
		String gid = request.getParameter("gid");
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
		List<TIcketTemplate> ticketTemp= ticketTemplateDao.findAll();
		
	System.out.println("===================="+cid+"------"+gid);
		//m.addAttribute("advLinks", user.getId());
		//sessionStatus.setComplete();
	TChildren children = new TChildren();
	   
		Page<TChildren> list = childrenService.findAll(name,cid,gid,p);
		m.addAttribute("classe", classe);
		m.addAttribute("grade", grade);
		 m.addAttribute("ticketTemp", ticketTemp);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("children", children);
		m.addAttribute("method", "get");
		return "/children/listy";
	}

	
	
	/**
	 * 学生信息
	 * @param m
	 * @return
	 * 
	 */

	@RequestMapping(value = "/{id}/read")
	public String readChildren(@PathVariable Long id, Model m) {
		TChildren children = childrenService.findChildrenById(id);
		
		String chargConnection = children.getChargConnection();

		List<TChargeItem> chargeitem = new ArrayList<TChargeItem>();
		if(!"".equals(chargConnection)&&chargConnection!=null){
			String[] ss = chargConnection.split(",");
			for(int i=0;i<ss.length;i++){
				chargeitem.add(chargeItemService.findChargeitemById(Long.valueOf(ss[i])));
			}
		}
		
		m.addAttribute("chargeitem", chargeitem);
	
		m.addAttribute("children", children);
		m.addAttribute("sexType", SexType.values());
		return "/children/read";
	}
	
	/**
	 * 学生收费设置
	 * @param m
	 * @return
	 * @throws Exception 
	 * 
	 */

	@RequestMapping(value = "/export")
	public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model m) throws Exception {
		
		List<TChildren> children=childrenDao.findAll();
		
		List<Map<String, Object>> result = childrenService.Childrenstatistics();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		HttpHeaders headers = new HttpHeaders();  

		
		headers.add("Content-Disposition","attachment;filename=" + format.format(new Date()) + ".xls");  
		return new ResponseEntity<byte[]>(childrenService.exportPersonnelsExcel(result).toByteArray(), headers, HttpStatus.OK);
	}

	/**
	 * 显示图片
	 * 
	 * @param sjId
	 * @param sjzlId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/findFile", method = RequestMethod.GET)
	public void getCachePicTypeId(@PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		TChildren file = childrenService.findChildrenById(id);
		String baseDir = Config.getString("uploadFileDir");
		if(file.getPhotoDir()!= null) {
			FileInputStream fis = new FileInputStream(file.getPhotoDir());
			OutputStream os = null;
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1)
				os.write(buffer, 0, count);
			fis.close();
			os.close();
		}
		
		
	}
	
	
	
	/**
	 * 初始化修改专家信息
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String childrenEdit(@PathVariable Long id, Model m) {
		
		if (!PubUtil.isEmpty(id) && -1L != id) {
			TChildren children=childrenService.findChildrenById(id);
		    if(StringUtils.isEmpty(children.getChildSchoolId())){
		    	children.setChildSchoolId(childrenService.getSystemSequence("contractSequence"));
		    }
			
			m.addAttribute("children", children);
		} else {
			m.addAttribute("children", new TChildren());
		}
		List<TChargeItem> Itemlist=chargeItemDao.findAll();
		List<TGrade> grade=gradeService.findAll();
		List<TClasses> classes=classesService.findAll();
		m.addAttribute("Itemlist", Itemlist);
		m.addAttribute("grade", grade);
		m.addAttribute("classe", classes);
		m.addAttribute("msg", "edit");
		return "children/createOrUpdate";
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
	public String editChildren(@PathVariable Long id,@RequestParam("photoDir") MultipartFile photoDir,@ModelAttribute("children") TChildren children,BindingResult bind, SessionStatus status, Model m)
			throws ParameterCheckException,IOException {
		//if (bind.hasErrors()) {
		//	return "employe/createOrUpdate";
		//}
		TChildren childrent = childrenService.findChildrenById(id);
		if (!photoDir.getOriginalFilename().isEmpty()||childrent.getPhotoDir().isEmpty()) {
			
				try {
					 children.setPhotoDir(fileservice.upphote(photoDir));
				}catch(Exception e) {
					e.printStackTrace();
					
						
				}
			  
			}else{
				children.setPhotoDir(childrent.getPhotoDir());
				//System.out.println("==================");
				
			}
		childrenService.saveChildren(children);
		m.addAttribute("employe", childrent);
		m.addAttribute("msg", "editOk");
		return "children/createOrUpdate";
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
	public String deleteEmploye(@PathVariable Long id) throws ParameterCheckException {
		try {
			 childrenService.deleteChildren(id);
			 return "ok";
		}catch(Exception e) {
			e.printStackTrace();
			 return "no";
		}
		
	}

}

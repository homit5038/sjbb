package com.xqx.frame.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.SexType;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.service.ChildrenService;
import com.xqx.frame.service.FileService;
import com.xqx.frame.service.GradeService;
import com.xqx.frame.util.PubUtil;

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
	public String newChildren(Model m){
		
		TChildren children = new TChildren();
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
	 * 新增专家
	 * @param m
	 * @param expert
	 * @return
	 * @throws ParameterCheckException
	 * @throws IOException 
	 */
	
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newClass(Model m,@ModelAttribute("children") TChildren children,BindingResult bind, SessionStatus status,@RequestParam("photoDir") MultipartFile multipartFile) throws ParameterCheckException,IOException{

		children.setPhotoDir(fileservice.upphote(multipartFile));
	
		String saveMsg = childrenService.saveChildren(children);
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/children/createOrUpdate";
		}else{
			return "redirect:/children/list";
		}
	}
	
	
	

	@RequestMapping("/list")
	public String childrenlist(Model m,
			//SessionStatus sessionStatus,
			@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("childName");
		String cid = request.getParameter("cid");
		String gid = request.getParameter("gid");
		List<TClasses> classe=classesService.findAll();
		List<TGrade> grade=gradeService.findAll();
		m.addAttribute("name", name);
	System.out.println("===================="+cid+"------"+gid);
		//m.addAttribute("advLinks", user.getId());
		//sessionStatus.setComplete();
		
		Page<TChildren> list = childrenService.findAll(name,cid,gid,p);
		m.addAttribute("classe", classe);
		m.addAttribute("grade", grade);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		return "/children/list";
	}
	/**
	 * 学生详细
	 * @param m
	 * @return
	 * 
	 */
	
	
	/**
	 * 学生收费设置
	 * @param m
	 * @return
	 * 
	 */

	@RequestMapping(value = "/{id}/read")
	public String readChildren(@PathVariable Long id, Model m) {
		TChildren children = childrenService.findChildrenById(id);
		m.addAttribute("children", children);
		m.addAttribute("sexType", SexType.values());
		return "/children/read";
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
			m.addAttribute("children", childrenService.findChildrenById(id));
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
		if (!photoDir.getOriginalFilename().isEmpty()) {
			
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
	public String deleteEmploye(@PathVariable Long id, Model m) throws ParameterCheckException {
		childrenService.deleteChildren(id);
		return "ok";
	}

}

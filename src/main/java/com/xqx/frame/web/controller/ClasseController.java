package com.xqx.frame.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.service.ChargeItemService;
import com.xqx.frame.service.ClassesService;
import com.xqx.frame.service.GradeService;

/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:/spring/root-context.xml"})*/
@Controller
@RequestMapping("/classe")
public class ClasseController {
	
	@Autowired
	ClassesService classesService;
	
	@Autowired
    GradeService gradeService;

	@Autowired
	TchargeItemDao chargeItemDao;
	
	@Autowired
	TClasseDao classeDao;
	/**
	 * 初始化新增专家
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newGrade(Model m){
		TClasses classes = new TClasses();
		/*PGJG[] pgjgs = PGJG.values();
		m.addAttribute("pgjgs", pgjgs);*/
		List<TGrade> grade=gradeService.findAll();
		m.addAttribute("grade", grade);
		m.addAttribute("classes", classes);
		m.addAttribute("msg", "new");
		return "/classe/createOrUpdate";
	}

	/**
	 * 新增专家
	 * @param m
	 * @param expert
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newClasses(Model m,@ModelAttribute("classes") TClasses classes) throws ParameterCheckException{
		//expert.setAvailability(1);
		/*PGJG[] pgjgs = PGJG.values();
		m.addAttribute("pgjgs", pgjgs);*/
		
		String saveMsg = classesService.saveClasses(classes);

		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/classe/createOrUpdate";
		}else{
			return "redirect:/classe/list";
		}
	}
	
	@RequestMapping("/list")
	public String list(Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("classesname");
		m.addAttribute("name", name);
		Page<TClasses> list = classesService.findAll(name, p);
		List<TChargeItem> Itemlist=chargeItemDao.findAll();
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("Itemlist", Itemlist);
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		return "/classe/list";
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
		TClasses classes = classesService.findClassesById(id);
	
		List<TGrade> grade=gradeService.findAll();
	
		m.addAttribute("classes", classes);
		m.addAttribute("grade", grade);
		m.addAttribute("msg", "edit");
		return "classe/createOrUpdate";
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
	public String editClasses(@ModelAttribute("classes") TClasses classes,
			BindingResult bind, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "classe/createOrUpdate";
		}
		classesService.saveClasses(classes);
		m.addAttribute("classes", classes);
		m.addAttribute("msg", "editOk");
		return "classe/createOrUpdate";
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
	public String deleteTClasses(@PathVariable Long id, Model m) throws ParameterCheckException {
		classesService.deleteClasses(id);
		return "ok";
	}

	/**
	 * 收费项设置
	 * 
	 * @param expert
	 * @param bind
	 * @param status
	 * @return
	 * @throws ParameterCheckException
	 */
	@ResponseBody
	@RequestMapping(value = "/charge")
	public String shfshzhi(Model m,String childIdList,String chargeIdList)
			throws ParameterCheckException,IOException {
		
		
		if(!StringUtils.isEmpty(childIdList)) {
			String[] ss = childIdList.split(",");
			System.out.println("==================="+childIdList);
			Set<TClasses> trade = new HashSet<TClasses>();
				for(int i=0;i<ss.length;i++){
					TClasses classe = classesService.findClassesById(Long.valueOf(ss[i]));
					System.out.println("==================="+chargeIdList);
					classe.setChargeitem(chargeIdList);
					trade.add(classe);
				}
				classeDao.save(trade);
			    return "ok";
		}
			else{
				return "no";
		}
		
	}
	
	
	/**
	 * 初始化导入数据
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/importExpert", method = RequestMethod.GET)
	public String initImpExpert(Model m) {
		Map<String, Object> map = new HashMap<String, Object>();
		m.addAttribute("map", map);
		return "expert/impExpert";
	}
	

	/**
	 * 下载专家库导入模板
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadExcel")
	public void downloadExcel(HttpServletResponse response,HttpServletRequest request) {
        try {
        	String fileName = "估价分会专家库系统导入专家模板.xls";
            //获取文件的路径
            String excelPath = request.getSession().getServletContext().getRealPath("/Excel/"+fileName);
            // 读到流中
            InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 循环取出流中的数据
            byte[] b = new byte[200];
            int len;

            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

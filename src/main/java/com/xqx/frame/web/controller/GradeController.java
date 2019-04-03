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

import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TUserRole;
import com.xqx.frame.security.RoleType;
import com.xqx.frame.service.FileService;
import com.xqx.frame.service.GradeService;


/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:/spring/root-context.xml"})*/
@Controller
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	GradeService gradeService;
	
	@Autowired
	TGradeDao gradeDao;
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 初始化新增专家
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newGrade(Model m){
		TGrade grade = new TGrade();
		/*PGJG[] pgjgs = PGJG.values();
		m.addAttribute("pgjgs", pgjgs);*/
		m.addAttribute("grade", grade);
		m.addAttribute("msg", "new");
		return "/grade/createOrUpdate";
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
	public String newGrade(Model m,@ModelAttribute("grade") TGrade grade,BindingResult bind, SessionStatus status,@RequestParam("photopath") MultipartFile photopath) throws ParameterCheckException, IOException{
		//expert.setAvailability(1);
		/*PGJG[] pgjgs = PGJG.values();
		m.addAttribute("pgjgs", pgjgs);*/
		//fileService.webUpload(multipartFiles);
		
		System.out.println("=======================");

		grade.setPhotopath(fileService.upphote(photopath));
		String saveMsg = gradeService.saveGrade(grade);
		m.addAttribute("grade", grade);
		//m.addAttribute("fileType", fileService.upphote(multipartFiles));
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/grade/createOrUpdate";
		}else{
			return "redirect:/grade/list";
		}
	}
	
	
	@RequestMapping(value = "/findlo", method = RequestMethod.GET)
	public String getCachePiclo(HttpServletRequest request, HttpServletResponse response){
		return "grade/lo";
	}
	
	
	
	@RequestMapping(value = "/fileuploads", method = RequestMethod.POST)
	public String fileuploads(@RequestParam("file") List<MultipartFile> multipartFiles, Model m)
			throws IOException {
		//fileService.upphote(multipartFiles);
		//m.addAttribute("fileType", fileService.upphote(multipartFiles));
	
		return "/grade/lo";
	}

	
	
	

	/**
	 * 显示图片
	 * 
	 * @param tabName
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/getPic", method = RequestMethod.GET)
	public void getPic(@PathVariable Long id,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=GBK");
		response.setContentType("image/jpeg");
		TGrade TGrade=gradeService.findGradeById(id);
		FileInputStream fis  = new FileInputStream(TGrade.getPhotopath());
		OutputStream os = null;
		os = response.getOutputStream();
		int count = 0;
		byte[] buffer = new byte[1024 * 8];
		while ((count = fis.read(buffer)) != -1)
			os.write(buffer, 0, count);
		fis.close();
		os.close();
	}

	
	
    /**
     * 获取机构logo
     *
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
   // @RequestMapping(value = "/showLogo", method = RequestMethod.GET)
    //public void showOrgLogo(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//TGrade grade = gradeService.findGradeById(id);
       // OutputStream os = response.getOutputStream();
      //  byte[] buffer = grade.getPhotoDir();
       // try {
         //   os.write(buffer);
          //  os.close();
       // } catch (Exception e) {
           // os.close();
        //}
    //}
	
	
	@RequestMapping("/list")
	public String list(Model m,@PageableDefault(page = 0, size = 10, sort = { "id" }, 
			direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("gradeName");
		m.addAttribute("name", name);
		
		Page<TGrade> list = gradeService.findAll(name, p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		return "/grade/list";
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
		TGrade grade = gradeService.findGradeById(id);
		m.addAttribute("grade", grade);
		m.addAttribute("msg", "edit");
		return "grade/createOrUpdate";
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
		String[] ss = childIdList.split(",");
		Set<TGrade> trade = new HashSet<TGrade>();
			for(int i=0;i<ss.length;i++){
				TGrade grade = gradeService.findGradeById(Long.valueOf(ss[i]));
				grade.setChargeitem(chargeIdList);
				trade.add(grade);
			}
			gradeDao.save(trade);
		//gradeService.ichargeitem(id, ster);
		//System.out.print(chargeIdList+"/n>");
		//System.out.print(childIdList+"/n>");
		
		//TGrade grade = gradeService.findGradeById(id);
		//grade.setChargeitem(name);
		//gradeService.saveGrade(grade);
		//m.addAttribute("grade", grade);
		//m.addAttribute("msg", "editOk");
		return "ok";
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
	public String editGrade(@ModelAttribute("grade") TGrade grade,
			BindingResult bind,@RequestParam("photopath") MultipartFile photopath,Model m)
			throws ParameterCheckException,IOException {
		//if (bind.hasErrors()) {
		//	return "grade/createOrUpdate";
		//}
		TGrade gradet=  gradeService.findGradeById(grade.getId());
		if(!photopath.isEmpty()) {
			grade.setPhotopath(fileService.upphote(photopath));
		}else {

			grade.setPhotopath(gradet.getPhotopath());
		}
	
		gradeService.saveGrade(grade);
		m.addAttribute("grade", grade);
		m.addAttribute("msg", "editOk");
	
		
		
		return "grade/createOrUpdate";
	}

	@ResponseBody
	@RequestMapping("/del")
	public String batchDeletes(HttpServletRequest request, HttpServletResponse response) {
		String items = request.getParameter("delitems");// System.out.println(items);
		String[] strs = items.split(",");
        String tems="no";
		for (int i = 0; i < strs.length; i++) {
			try {
				int a = Integer.parseInt(strs[i]);
				gradeService.deleteGrade(a);
				tems="ok";
			} catch (Exception e) {
				tems="no";
			}
		}
		return tems;
		
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
		gradeService.deleteGrade(id);
		return "ok";
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

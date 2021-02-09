package com.xqx.frame.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqx.frame.model.TKindergarten;
import com.xqx.frame.util.Validator;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.TEmployeDao;
import com.xqx.frame.dao.TExpertDao;
import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.SexType;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.query.employeQuery;
import com.xqx.frame.service.ClassesService;
import com.xqx.frame.service.EmployeService;
import com.xqx.frame.service.FileService;
import com.xqx.frame.util.PubUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:/spring/root-context.xml"})*/
@Controller
@RequestMapping("/employe")
@SessionAttributes({ "advLinks","editFiles" })
public class EmployeController {
		
	private static final int limit = 0;
	private static final int page = 0;
	@Autowired	
	EmployeService employeSerivce;
	@Autowired
	TEmployeDao employeDao;
	
	@Autowired	
	ClassesService classesSerivce;
	
	@Autowired
	TExpertDao expertDao;
	
	@Autowired
	TGradeDao gradeDao;

	@Autowired
	FileService fileservice;

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
	 * 职工详细
	 * @param m
	 * @return
	 * 
	 */

	@RequestMapping(value = "/{id}/read")
	public String readEmploye(@PathVariable Long id, Model m) {
		TEmploye employe = employeSerivce.findEmployeById(id);
		m.addAttribute("employe", employe);
		m.addAttribute("sexType", SexType.values());
		m.addAttribute("msg", "edit");
		return "employe/read";
	}
    
    
	/**
	 * 初始化新增职工
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newEmploye(Model m){

		TEmploye employe = new TEmploye();
		m.addAttribute("employe", employe);
		m.addAttribute("sexType", SexType.values());
		m.addAttribute("msg", "new");
		return "/employe/createOrUpdate";
	}



	/**
	 * 保存职工信息
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newClass(Model m,
						   HttpServletRequest request,
						   @ModelAttribute("employe") TEmploye employe,BindingResult bind, SessionStatus status,@RequestParam("photoDir") MultipartFile multipartFile) throws ParameterCheckException,IOException{
		//bindern.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		//employe.setInGartenDate(new Date());
		//employe.setBirthday(new Date());


		Object topId=request.getSession().getAttribute("topId");
		if (!Validator.isNull(topId)) {
			long topIds=Long.valueOf(topId.toString());
			TKindergarten kindergarten=new TKindergarten();
			kindergarten.setId(topIds);
			employe.setKindergarten(kindergarten);
		}

		employe.setPhotoDir(fileservice.upphote(multipartFile));
		String saveMsg = employeSerivce.saveEmploye(employe);
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/employe/createOrUpdate";
		}else{
			return "redirect:/employe/list";
		}
	}






	@ResponseBody
	@RequestMapping(value = "/wxedit",method=RequestMethod.GET)
	public TEmploye wxnewEmploye(Model m){
		TEmploye employe = new TEmploye();
		return employe;
	}
	
	@ResponseBody
	@RequestMapping(value = "/wxedit/{id}", method = RequestMethod.GET)
	public TEmploye wxedit(@PathVariable Long id) {
		TEmploye employe;
		if (!PubUtil.isEmpty(id) && -1L != id) {
			 employe=employeSerivce.findEmployeById(id);
		} else {
			 employe=new TEmploye();
		
		}
		//SexType sexType=SexType.values();
		
	
		
		return employe;
	}





@ResponseBody
@RequestMapping(value = "/weixinupice",method=RequestMethod.POST)
public JSONObject mnewsaveClass(@RequestParam("photoDir") MultipartFile photoDir,HttpServletRequest request) throws  ParameterCheckException,IOException{ 
	//System.out.println("<br/>============="+fileservice.upphote(photoDir)+"==============================");
	JSONObject jsonObj = new JSONObject();
	  String usersp=request.getParameter("user");
	jsonObj.put("photoDir",fileservice.upphote(photoDir));
	jsonObj.put("users",usersp);
	return jsonObj;
}


	@ResponseBody
	@RequestMapping(value = "/createui", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public String addUsers(@RequestBody TEmploye employe) throws ParameterCheckException {
		String result;
		String saveMsg = employeSerivce.saveEmploye(employe);
		if("exist".equals(saveMsg)){
			result = "no";
		}else{
			result = "ok";
		}
		return result;
	}

/*
	@ResponseBody
	@RequestMapping(value = "/createui", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public String addemploye(@RequestBody TEmploye employe) throws ParameterCheckException {
		String result;
		String saveMsg = employeSerivce.saveEmploye(employe);
		if("exist".equals(saveMsg)){
			result = "no";
		}else{
			result = "ok";
		}
		return result;
	}
*/


@ResponseBody
@RequestMapping(value = "/create",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
public String mnewsaveClass(@RequestParam Map<String, String> params,@RequestBody JSONObject body) throws ParameterCheckException{ 
//public TKindergarten login(@RequestBody TKindergarten params) {
	// @RequestBody  Map<String, Object> params//
	//String kindergartennames=params.getKindergartenname();
	//String principal=params.getPrincipal();
   // String Kindergartenname=request.getParameter("params");
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");     
	String result = "no";
	JSONObject jObject = new JSONObject();
	JSONObject json = JSONObject.fromObject(body);
	//JSONArray jsonArray=json.getJSONArray("params");
	//JSONArray jsonArrays=json.getJSONArray("academicQualification");
	
	JSONArray jsonArray=json.getJSONArray("academicQualification");
	JSONArray sexTypejsonArray=json.getJSONArray("sexType");

	
	
	System.out.println("============="+jsonArray.getJSONObject(3).get("id"));
	Date now = new Date();
	//json=jsonArray.getJSONObject(0);
	//jsonArray.getString("employeName");
	Long countryInde=Long.valueOf(json.getString("countryIndex"));
	TEmploye employe=new TEmploye();
	employe.setEmployeName(json.getString("employeName"));
	employe.setGraduateSchool(json.getString("graduateSchool"));
	employe.setEmployeEmail(json.getString("employeEmail"));
	String academicQualificationid = null;
	String sexTypeid = null;
	if(jsonArray.size()>0){
//	    for (java.util.Iterator tor=backBodyJson.iterator();tor.hasNext();) {
//	    JSONObject job = (JSONObject)tor.next();
//	    System.out.println(job.get("name"));
//	    System.out.println(job.get("age"));
//	    }
			for(int i=0;i<jsonArray.size();i++){
				System.out.println("============="+jsonArray.getJSONObject(i).get("checked"));
				if((boolean) jsonArray.getJSONObject(i).get("checked")) {
					 academicQualificationid=jsonArray.getJSONObject(i).getString("id");
					 
				}
	
			}
	}
	if(sexTypejsonArray.size()>0){
			for(int i=0;i<sexTypejsonArray.size();i++){
				System.out.println("============="+sexTypejsonArray.getJSONObject(i).get("checked"));
				if((boolean) sexTypejsonArray.getJSONObject(i).get("checked")) {
					sexTypeid=sexTypejsonArray.getJSONObject(i).getString("id");
					 
				}
	
			}
	}
	employe.setAcademicQualification(academicQualificationid);
	employe.setSexType(sexTypeid);
	employe.setIdcardNumber(json.getString("idcardNumber"));
	employe.setJobNumber(json.getString("jobNumber"));
	employe.setDwellingPlace(json.getString("dwellingPlace"));
	employe.setNation(json.getString("nation"));
	employe.setPhotoDir(json.getString("photoDir"));
	String inGartenDate=json.getString("inGartenDate");
	String birthday=json.getString("birthday");
	
	String beginToWorkDate=json.getString("beginToWorkDate");
	  try {    
	        employe.setInGartenDate(format1.parse(inGartenDate));
	    	employe.setBeginToWorkDate(format1.parse(beginToWorkDate));    
	    	employe.setBirthday(format1.parse(birthday));
	 } catch (ParseException e) {    
	         e.printStackTrace();    
	  }   
	employe.setPoliticalBackground(json.getString("politicalBackground"));
	employe.setBirthPlace(json.getString("birthPlace"));
	employe.setPhoneNum(json.getString("phoneNum"));
	employe.setWorkAttendanceCardNumber(json.getString("workAttendanceCardNumber"));
	String saveMsg = employeSerivce.saveEmploye(employe);
	if("exist".equals(saveMsg)){
		result = "no";
	}else{
		result = "ok";
	}
	//System.out.println("<br/>============="+json.getString("principal")+"==============================");
	return result;
}    
/**
 * 删除员工信息
 * 
 * @param Employe
 * @return
 * @throws ParameterCheckException
 */

@ResponseBody
@RequestMapping(value = "/wxdelete", method = RequestMethod.POST)
public String wxDeleteEmploye(HttpServletRequest request) throws ParameterCheckException,IOException {
	String id=request.getParameter("id");
	employeSerivce.deleteEmploye(Long.valueOf(id));
	return "ok";
}

/**
 * 处理修改员工信息
 * 
 * @param Employe
 * @return
 * @throws ParameterCheckException
 */

@ResponseBody
@RequestMapping(value = "/wxedit/{id}", method = RequestMethod.POST)
public String wxeditEmploye(@PathVariable Long id,
		@RequestParam Map<String, String> params,
		@RequestBody JSONObject body)
		throws ParameterCheckException,IOException {

	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");     
	String result = "no";
	JSONObject jObject = new JSONObject();
	JSONObject json = JSONObject.fromObject(body);
	//JSONArray jsonArray=json.getJSONArray("params");
	//JSONArray jsonArrays=json.getJSONArray("academicQualification");
	
	JSONArray jsonArray=json.getJSONArray("academicQualificationt");
	JSONArray sexTypejsonArray=json.getJSONArray("sex");

	
	
	
	Date now = new Date();
	//json=jsonArray.getJSONObject(0);
	//jsonArray.getString("employeName");
	Long countryInde=Long.valueOf(json.getString("countryIndex"));
	Long tid=Long.valueOf(json.getString("id"));
	System.out.println("=============wwww"+tid);
	//TEmploye employe=new TEmploye();
	TEmploye employe = employeSerivce.findEmployeById(tid);
	employe.setId(tid);
	employe.setEmployeName(json.getString("employeName"));
	employe.setGraduateSchool(json.getString("graduateSchool"));
	employe.setEmployeEmail(json.getString("employeEmail"));
	String academicQualificationid = null;
	String sexTypeid = null;
	if(jsonArray.size()>0){
//	    for (java.util.Iterator tor=backBodyJson.iterator();tor.hasNext();) {
//	    JSONObject job = (JSONObject)tor.next();
//	    System.out.println(job.get("name"));
//	    System.out.println(job.get("age"));
//	    }
			for(int i=0;i<jsonArray.size();i++){
				System.out.println("============="+jsonArray.getJSONObject(i).get("checked"));
				if((boolean) jsonArray.getJSONObject(i).get("checked")) {
					 academicQualificationid=jsonArray.getJSONObject(i).getString("id");
					 
				}
	
			}
	}
	if(sexTypejsonArray.size()>0){
			for(int i=0;i<sexTypejsonArray.size();i++){
				System.out.println("============="+sexTypejsonArray.getJSONObject(i).get("checked"));
				if((boolean) sexTypejsonArray.getJSONObject(i).get("checked")) {
					sexTypeid=sexTypejsonArray.getJSONObject(i).getString("id");
					 
				}
	
			}
	}
	employe.setAcademicQualification(academicQualificationid);
	employe.setSexType(sexTypeid);
	employe.setIdcardNumber(json.getString("idcardNumber"));
	employe.setJobNumber(json.getString("jobNumber"));
	employe.setDwellingPlace(json.getString("dwellingPlace"));
	employe.setNation(json.getString("nation"));
	employe.setPhotoDir(json.getString("photoDir"));
	String inGartenDate=json.getString("inGartenDate");
	String birthday=json.getString("birthday");
	
	String beginToWorkDate=json.getString("beginToWorkDate");
	  try {    
	        employe.setInGartenDate(format1.parse(inGartenDate));
	    	employe.setBeginToWorkDate(format1.parse(beginToWorkDate));    
	    	employe.setBirthday(format1.parse(birthday));
	 } catch (ParseException e) {    
	         e.printStackTrace();    
	  }   
	employe.setPoliticalBackground(json.getString("politicalBackground"));
	employe.setBirthPlace(json.getString("birthPlace"));
	employe.setPhoneNum(json.getString("phoneNum"));
	employe.setWorkAttendanceCardNumber(json.getString("workAttendanceCardNumber"));
	System.out.println("<br/>============="+employe.getGraduateSchool()+"==============================");
	String saveMsg = employeSerivce.saveEmploye(employe);
	if("ok".equals(saveMsg)){
		result = "ok";
	}else{
		result = "n5o";
	}
	//System.out.println("<br/>============="+json.getString("principal")+"==============================");
	return saveMsg;
	


}




	
	@RequestMapping("/lish")
	public String lish(Model m) {
		return "/employe/lish";
	}
	
	@ResponseBody
	@RequestMapping("/listajax")
	public Object lists(Model m,Integer page, Integer limit, @PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("employeName");
		m.addAttribute("name", name);
		List<TEmploye> list = employeDao.findAll();
		//Page<TEmploye> all = employeSerivce.findAll(name, page - 1, limit);
	   // int totals= (int) list.getTotalElements();
		//return new ResultMap<T>("",0,page,limit,list);
	  //  return new ResultMap<T>("",list,0,limit);
		//return new PageQueryResult<>(list);
		return new PageQueryResult<>(list);
		
	}
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/wxlist")
	public Object wxlist(Integer page, Integer limit, employeQuery query){
		//String name = request.getParameter("employeName");
		//System.out.println("========================="+p.getPageNumber());
		Page<TEmploye> list = employeSerivce.findAllt(query,page - 1,limit);
		return new PageQueryResult<>(list);

	}

	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/listaja")
	public Object lish(Integer page, Integer limit, employeQuery query){
		//String name = request.getParameter("employeName");
		//System.out.println("========================="+p.getPageNumber());
		Page<TEmploye> list = employeSerivce.findAllt(query,page - 1,limit);
		
		//List<TEmploye> list = employeDao.findAll();
		//return new ResultMap<T>(list);
		return new PageQueryResult<>(list);

	}

	
	//@RequestMapping(value="/query")
	//@ResponseBody
	//public JSONObject query(Integer page,Integer limit,String name){
		//List<TEmploye> sylist = employeSerivce.query(name);
		//return PageQueryResult(page,limit,sylist);
		
	//}
	



	@RequestMapping("/list")
	public String list(Model m,
			//SessionStatus sessionStatus,
			@PageableDefault(page = 0, size = 10,direction = Direction.DESC) Pageable p,HttpServletRequest request){
		String name = request.getParameter("employeName");
		
			
		m.addAttribute("name", name);
		//m.addAttribute("advLinks", user.getId());
		//sessionStatus.setComplete();
		Page<TEmploye> list = employeSerivce.findAll(name,p);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", list.getTotalElements());
		m.addAttribute("data", list);
		m.addAttribute("method", "get");
		return "/employe/list";
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
		
		if (!PubUtil.isEmpty(id) && -1L != id) {
			m.addAttribute("employe", employeSerivce.findEmployeById(id));
		} else {
			m.addAttribute("employe", new TEmploye());
		}
		m.addAttribute("sexType", SexType.values());
		m.addAttribute("msg", "edit");
		return "employe/createOrUpdate";
	}

	
/*	@RequestMapping(value = "/{action}",method = RequestMethod.GET)*/
	@RequestMapping(value = "/layui/{action}",method = RequestMethod.GET)
	public String forward(Model m, @PathVariable String action, Long id) {

		if (!PubUtil.isEmpty(id) && -1L != id) {
			m.addAttribute("employe", employeSerivce.findEmployeById(id));
		} else {
			m.addAttribute("employe", new TEmploye());
		}
		m.addAttribute("sexType", SexType.values());
		m.addAttribute("msg", "edit");
		return "employe/info";
	}
	
	/**
	 * 处理修改员工信息
	 * 
	 * @param Employe
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editEmploye(@PathVariable Long id,@RequestParam("photoDir") MultipartFile photoDir,@ModelAttribute("employe") TEmploye employe,BindingResult bind, SessionStatus status, Model m)
			throws ParameterCheckException,IOException {
		//if (bind.hasErrors()) {
		//	return "employe/createOrUpdate";
		//}
		
		TEmploye employet = employeSerivce.findEmployeById(id);
		if (!photoDir.getOriginalFilename().isEmpty()) {
			
				try {
					 employe.setPhotoDir(fileservice.upphote(photoDir));
				}catch(Exception e) {
					e.printStackTrace();
					
						
				}
			  
			}else{
				employe.setPhotoDir(employet.getPhotoDir());
				//System.out.println("==================");
				
			}
		employeSerivce.saveEmploye(employe);
		m.addAttribute("employe", employe);
		m.addAttribute("msg", "editOk");
		return "employe/createOrUpdate";
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
	@ResponseBody
/*	@RequestMapping(value = "/{action}/{id}")*/
	@RequestMapping(value = "/layui/{action}/{id}")
	public QueryResult<?> addajaxpost(
			@PathVariable Long id,
			String action,
			@ModelAttribute("employe") TEmploye employe,BindingResult bind, SessionStatus status, Model m)
			throws ParameterCheckException,IOException {



		employeSerivce.saveEmploye(employe);
		//m.addAttribute("employe", employe);
		//m.addAttribute("msg", "editOk");
		return new QueryResult<>("保存成功");
		
	}
	   /**
     * 上传模板（文件）
     *
     * @return
	 * @throws IOException 
     */
	@ResponseBody
	@RequestMapping(value = "/addFile" , method = RequestMethod.POST)
    public QueryResult addFile(@RequestParam("file") MultipartFile printFile) throws IOException {
    	String docTemp = fileservice.upphote(printFile);
    	return new QueryResult<>(docTemp,"上传成功");
    }
	
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public QueryResult<?> delete(@PathVariable("id") Long id) {
		employeSerivce.deleteEmploye(id);
		return new QueryResult<>("删除成功");
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
		employeSerivce.deleteEmploye(id);
		return "ok";
	}

	/**
	 * 初始化导入数据
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/importExpert", method = RequestMethod.GET)
	public String initImpGrade(Model m) {
		Map<String, Object> map = new HashMap<String, Object>();
		m.addAttribute("map", map);
		return "calsses/impExpert";
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
		TEmploye file = employeSerivce.findEmployeById(id);
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
	 * 导入专家信息
	 * @param m
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExpert", method = RequestMethod.POST)
	public String importEmploye(Model m,@RequestParam("files") MultipartFile multipartFile) throws Exception{
		Map<String, Object> map = employeSerivce.impData(multipartFile);
		m.addAttribute("map", map);
		return "calsses/impExpert";
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

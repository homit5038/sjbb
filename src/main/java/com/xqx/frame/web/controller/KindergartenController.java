package com.xqx.frame.web.controller;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.query.employeQuery;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.UserManager;
import com.xqx.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqx.frame.dao.TIcketTemplateDao;
import com.xqx.frame.dao.TKindergartenDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TKindergarten;
import com.xqx.frame.model.dto.TKindergartenQueryObject;
import com.xqx.frame.service.KindergartenService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.support.SessionStatus;


/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/root-context.xml"})*/
@Controller
@RequestMapping("/kindergarten")
public class  KindergartenController {

	@Autowired
	KindergartenService kindergartenService;
	@Autowired
	TKindergartenDao kindergartenDao;
	@Autowired
	private UserManager userManagerService;
	@Autowired
	TIcketTemplateDao ticketTemplateDao;

	/**
	 * 初始化新增专家
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new",method=RequestMethod.GET)
	public String newGrade(Model m){
		TKindergarten kindergarten = new TKindergarten();
		m.addAttribute("kindergarten", kindergarten);
		m.addAttribute("msg", "new");
		return "/kindergarten/createOrUpdate";
	}


	/**
	 * 新增幼儿园
	 * @throws ParameterCheckException
	 * @throws
	 */

	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public String newKindergartss(Model m,@ModelAttribute("kindergarten") TKindergarten kindergarten) throws ParameterCheckException{
		String saveMsg =  kindergartenService.saveKindergarten(kindergarten);
		if("exist".equals(saveMsg)){
			m.addAttribute("msg", "exist");
			return "/kindergarten/createOrUpdate";
		}else{
			return "redirect:/kindergarten/list";
		}
	}



	@ResponseBody
	@RequestMapping(value = "/find/{id}")
	public Object findkindergarteanduser(@PathVariable Long id,Integer page, Integer limit){
		//List<TKindergarten> kindergarten=userManagerService.findUserById(id).getKindergarten();

		Page<TKindergarten> list = kindergartenService.findAll(new PageRequest(page - 1, limit));


/*		Set<String> list=new HashSet<>();
		for (TKindergarten pid:kindergarten) {
			list.add(pid.getKindergartenname());
		}*/

		//PageQueryResult<List<TUser>> listPageQueryResult = new PageQueryResult<>(hh);
		//return new PageQueryResult<>(list);
		return new QueryResult<>(list,"oooooooo");
	}

	@RequestMapping(value = {"/selectKindergarten","/{id}/selectKindergarten"},method = RequestMethod.GET)
	public String selectchildrenlist(Model m,TKindergarten kindergarten) {
		TUser user = (TUser) SecurityUtil.getCurrentUser();
		List<TKindergarten> list = null;
		if (Validator.isNull(kindergarten.getCreateUserID())) {
			list = kindergartenDao.findKindergartenByUserId(user.getId());
		}
		m.addAttribute("kindergarten", list);
		return "/kindergarten/selectKindergarten";
	}




	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public String addUser(@RequestBody TKindergarten kindergarten) throws ParameterCheckException {

		String result;
	//	TKindergarten kindergarten = (TKindergarten) JSONObject.toBean(body,TKindergarten.class);
		String saveMsg =  kindergartenService.saveKindergarten(kindergarten);
		if("exist".equals(saveMsg)){
			result = "no";
		}else{
			result = "ok";
		}

		return "ok";
	}



	@ResponseBody
	@RequestMapping(value = "/createdda",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String login(@RequestParam Map<String, String> params,@RequestBody JSONObject body) throws ParameterCheckException{
//public TKindergarten login(@RequestBody TKindergarten params) {
		// @RequestBody  Map<String, Object> params//
		//String kindergartennames=params.getKindergartenname();
		//String principal=params.getPrincipal();
		// String Kindergartenname=request.getParameter("params");
		String result = "no";
		JSONObject jObject = new JSONObject();
		JSONObject json = JSONObject.fromObject(body);
		JSONArray jsonArray=json.getJSONArray("params");
		Date now = new Date();
		json=jsonArray.getJSONObject(0);
		TKindergarten kindergarten=new TKindergarten();
		kindergarten.setKindergartenname(json.getString("kindergartenname"));
		kindergarten.setKinderPhoneNumber(json.getString("kinderPhoneNumber"));
		kindergarten.setPrincipal(json.getString("principal"));
		kindergarten.setKinderQq(json.getString("kinderQq"));
		kindergarten.setAddresea(json.getString("addresea"));
		kindergarten.setDuetime(now);
		String saveMsg =  kindergartenService.saveKindergarten(kindergarten);
		if("exist".equals(saveMsg)){
			result = "no";
		}else{
			result = "ok";
		}
		System.out.println("<br/>============="+json.getString("principal")+"==============================");

	/*System.out.println("<br/>============="+json+"==============================");
	System.out.println("<br/>============="+jsonArray+"==============================");
	System.out.println("<br/>============="+kindergartenname+"==============================");*/

		return result;
	}





	@ResponseBody
	@RequestMapping(value = "/createuu",method=RequestMethod.POST)
	public String mobilenewKindergartss(@RequestParam(value = "params") String[] titles){

//	System.out.println("<br/>============="+titles[0]["titles"]+"==============================");
/*for(int i=0;i<titles.length;i++)
{
	System.out.println("<br/>"+titles.length+"============="+titles[i]);
}
*/
/*String[] strs = titles;
//String数组转List
List<String> strsToList1= Arrays.asList(strs);
for(String s:strsToList1){
//System.out.println(s);
System.out.println("<br/>============="+s.toString());
}*/


		//String saveMsg =  kindergartenService.saveKindergarten(kindergarten);
		//if("exist".equals(saveMsg)){
		//m.addAttribute("msg", "exist");
		return "no";
		//}else{
		//return "yes";
		//}
	}
	@ResponseBody
	@RequestMapping(value = "/listtyy")
	public String enrollList(HttpServletRequest request,
							 @ModelAttribute TKindergartenQueryObject trainDto,
							 @PageableDefault (page = 0, size = 10, sort = {"createTime"},direction = Direction.DESC) Pageable pageable){
		String ss=trainDto.getKindergartenname();
//System.out.print("---------------------------"+trainDto.getPrincipal());
		return ss;
	}




	@RequestMapping(value = "/list")
	public String list(Model m,HttpServletRequest request, @ModelAttribute TKindergartenQueryObject  queryObject,
					   @PageableDefault (page = 0, size = 10, sort = {"createTime"},direction = Direction.DESC) Pageable p
	){
		//String name = request.getParameter("Kindergartenname");
//	List<TKindergarten> lists = kindergartenService.findKindergartenByName(name);
		//List<TKindergarten> listt= kindergartenDao.findAll();
		Page<TKindergarten> TKindergartenList = kindergartenService.findAll(p,queryObject);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", TKindergartenList.getTotalElements());
		//List<TIcketTemplate> ticketTemp= ticketTemplateDao.findAll();
		m.addAttribute("data", TKindergartenList);
		return "/kindergarten/list";
	}




	@RequestMapping(value = "/selectList")
	public String selectList(Model m,
							 @PageableDefault (page = 0, size = 10, sort = {"createTime"}, direction = Direction.DESC) Pageable p,
							 SessionStatus status)
	throws ParameterCheckException {
		Page<TKindergarten> TKindergartenList = kindergartenService.findAll(p);
		m.addAttribute("data", TKindergartenList);
		 return "/kindergarten/selectlist";
	};

	/**
	 * 初始化修改专家信息
	 *
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String initEdit(@PathVariable Long id, Model m) {

		TKindergarten kindergarten = kindergartenService.findKindergartenById(id);
		String kindergartens = null;
		if (!Validator.isNull(kindergarten.getFid())) {
			kindergartens = kindergartenService.findKindergartenById(kindergarten.getFid()).getKindergartenname();
			m.addAttribute("fidname", kindergartens);
		}
		m.addAttribute("kindergarten", kindergarten);
		m.addAttribute("msg", "edit");
		return "kindergarten/createOrUpdate";
	}

	/**
	 * 处理修改专家信息
	 * @throws ParameterCheckException
	 */

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editGrade(@ModelAttribute("kindergarten") TKindergarten kindergarten,Model m)
			throws ParameterCheckException{
		//if (bind.hasErrors()) {
		//	return "grade/createOrUpdate";
		//}


		kindergartenService.saveKindergarten(kindergarten);
		m.addAttribute("kindergarten", kindergarten);
		m.addAttribute("msg", "editOk");
		return "kindergarten/createOrUpdate";
		//return "/kindergarten/list";
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
		kindergartenService.deleteTKindergarten(id);
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


}

package com.xqx.frame.mobile.controller.admin;

import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/mobile")
public class MClassesController {

   @Autowired
   TClasseDao classesDao;

	@ResponseBody
	@RequestMapping(value = "/classes/list",method = RequestMethod.GET)
	public QueryResult list(HttpServletRequest request, HttpServletResponse response){
		//response.setHeader("Access-Control-Allow-Origin", "*");
		List<TClasses> lists = classesDao.findAll();
		return new QueryResult<>(lists);
	}
	

}

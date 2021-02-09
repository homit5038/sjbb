package com.xqx.frame.mobile.controller.admin;

import java.util.List;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.service.GradeService;

@Controller
@RequestMapping("/mobile")
public class MGradeController {
	
	
	@Autowired
	GradeService gradeService;
	
	@Autowired
	TGradeDao gradeDao;


	@ResponseBody
	@RequestMapping(value = "/grade/list",method = RequestMethod.GET)
	public QueryResult list(HttpServletRequest request, HttpServletResponse response){
		//response.setHeader("Access-Control-Allow-Origin", "*");
		List<TGrade> lists = gradeDao.findAll();
        return new QueryResult<>(lists);

	}


}

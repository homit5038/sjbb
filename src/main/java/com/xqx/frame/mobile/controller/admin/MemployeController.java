package com.xqx.frame.mobile.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.xqx.frame.dao.PropertyDao;
import com.xqx.frame.form.PageQueryResult;
import com.xqx.frame.form.QueryResult;
import com.xqx.frame.model.SexType;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.query.employeQuery;
import com.xqx.frame.service.EmployeService;
import com.xqx.frame.tags.PropertyTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/m/employe")
public class MemployeController {

	@Autowired
	EmployeService employeSerivce;
	@Autowired
	PropertyDao propertyDao;

	/**
	 * 职工列表
	 * @param
	 * @return
	 *
	 */

	@ResponseBody
	@RequestMapping("/employelist")
	public Object employelist(Integer page, Integer limit, employeQuery query){
		Page<TEmploye> list = employeSerivce.findAllt(query,page - 1,limit);
		return new PageQueryResult<>(list);
	}

	/**
	 * 职工详细
	 * @param m
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "/emDetails/{id}")
	public QueryResult emDetails(@PathVariable Long id, Model m) {
		JSONObject employe = employeSerivce.findEmployeByIds(id);
			return new QueryResult<>(employe);
	}

}

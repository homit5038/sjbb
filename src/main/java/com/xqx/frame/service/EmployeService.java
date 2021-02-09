package com.xqx.frame.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageResult;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.query.employeQuery;
public interface EmployeService {
	
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveEmploye(TEmploye employe) throws ParameterCheckException;
	
	/**
	 * 根据id查找专家
	 * @return
	 */
	public TEmploye findEmployeById(long id);



	/**
	 * 根据id查找专家
	 * @return
	 */
	public JSONObject findEmployeByIds(long id);
	/**
	 * 根据姓名查找专家
	 * @param Name
	 * @return
	 */
	public List<TEmploye> findEmployeByName(String name);
	
	
	Page<TEmploye> findAllt(employeQuery queryVo, Integer page, Integer size);
	
	/**
	 * 查找所有专家信息
	 * @return
	 */
	public Page<TEmploye> findAll(String name,Pageable pageable);
	
	public List<TEmploye> findAll();
	/**
	 * 根据专家姓名、评估机构、电话号码、邮箱、有效性查找专家
	 * @return
	 */
	public List<TEmploye> findEmployeByAll(String name,String idcardNumber,String email,String phone);
	/**
	 * 删除专家
	 * @param id
	 */
	public void deleteEmploye(long id);
	
	

	
	
	
	/**
	 * 外部excel导入专家信息
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> impData(MultipartFile multipartFile)
			throws Exception; 
	
	
}

package com.xqx.frame.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;

public interface ExpertService {
	
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveExpert(TExpert expert) throws ParameterCheckException;
	
	/**
	 * 根据id查找专家
	 * @return
	 */
	public TExpert findExpertById(long id);
	
	/**
	 * 根据姓名查找专家
	 * @param Name
	 * @return
	 */
	public List<TExpert> findExpertByName(String name);
	
	/**
	 * 查找所有专家信息
	 * @return
	 */
	public Page<TExpert> findAll(final String name,final String pgjg,final String email,final String phone,
			final String yxx,final String flag,final String beginTime, final String endTime,Pageable pageable);
	
	public List<TExpert> findAll();
	/**
	 * 根据专家姓名、评估机构、电话号码、邮箱、有效性查找专家
	 * @return
	 */
	public List<TExpert> findExpertByAll(String name,String pgjg,String email,String phone);
	/**
	 * 删除专家
	 * @param id
	 */
	public void deleteExpert(long id);
	
	/**
	 * 外部excel导入专家信息
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> impData(MultipartFile multipartFile)
			throws Exception; 
	
	
}

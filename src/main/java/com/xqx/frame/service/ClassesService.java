package com.xqx.frame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TClasses;

public interface ClassesService {
	
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveClasses(TClasses classes) throws ParameterCheckException;
	
	/**
	 * 根据id查找专家
	 * @return
	 */
	public TClasses findClassesById(long id);
	
	/**
	 * 根据姓名查找专家
	 * @param Name
	 * @return
	 */
	public List<TClasses> findClassesByClassesName(String name);
	
	/**
	 * 查找所有专家信息
	 * @return
	 */
	public Page<TClasses> findAll(final String name,Pageable pageable);
	
	
	public List<TClasses> findAll();

	/**
	 * 删除专家
	 * @param id
	 */
	public void deleteClasses(long id);
	

	
	
}

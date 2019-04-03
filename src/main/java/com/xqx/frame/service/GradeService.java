package com.xqx.frame.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TGrade;

public interface GradeService {
	
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveGrade(TGrade grade) throws ParameterCheckException,IOException;
	
	
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveGradePhote(TGrade grade,MultipartFile photoDir) throws ParameterCheckException,IOException;
	
	/**
	 * 根据id查找专家
	 * @return
	 */
	public TGrade findGradeById(long id);
	
	/**
	 * 根据姓名查找专家
	 * @param Name
	 * @return
	 */
	public List<TGrade> findGradeByGradeName(String name);
	
	/**
	 * 查找所有专家信息
	 * @return
	 */
	public Page<TGrade> findAll(final String name,Pageable pageable);
	
	
	public List<TGrade> findAll();

	/**
	 * 删除专家
	 * @param id
	 */
	public void deleteGrade(long id);
	

	/**
	 * 删除专家
	 * @param id
	 */
	public void ichargeitem(long id,String ster);
	
}

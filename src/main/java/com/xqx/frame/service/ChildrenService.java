package com.xqx.frame.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TChildren;


public interface ChildrenService {
	/**
	 * 保存专家信息
	 * @param expert
	 * @throws ParameterCheckException 
	 */
	public String saveChildren(TChildren children) throws ParameterCheckException;
	
	/**
	 * 根据id查找专家
	 * @return
	 */
	public TChildren findChildrenById(long id);
	
	/**
	 * 根据姓名查找专家
	 * @param Name
	 * @return
	 */
	public List<TChildren> findChildrenByName(String name);
	
/*	
	Page<TChildren> findAllt(childrenQuery queryVo, Integer page, Integer size);
	*/
	/**
	 * 查找所有专家信息
	 * @return
	 */
	public Page<TChildren> findAll(final String name,final String cid, final String gid,Pageable pageable);
	
	public List<TChildren> findAll();
	/**
	 * 根据专家姓名、评估机构、电话号码、邮箱、有效性查找专家
	 * @return
	 */
	public List<TChildren> findChildrenByAll(String name,String idcardNumber,String email,String phone);
	/**
	 * 删除专家
	 * @param id
	 */
	public void deleteChildren(long id);
	

	/**
	 * 外部excel导入专家信息
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> impData(MultipartFile multipartFile)
			throws Exception; 
}

package com.xqx.frame.service;

import java.util.Map;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpertBatch;

/**
 * 批次管理Service
 * 
 * @author DELL
 *
 */
public interface ExpertBatchService {

	public Map<String, Object> findBatch();
	
	/**
	 * 根据id获取批次
	 * 
	 * @param id
	 * @return
	 */
	public TExpertBatch findExpertBatchById(long id);

	/**
	 * 保存、更改批次
	 * 
	 * @param u
	 * @throws ParameterCheckException
	 */
	public void saveExpertBatch(TExpertBatch u) throws ParameterCheckException;

	/**
	 * 删除批次
	 * 
	 * @param id
	 */
	public void deleteExpertBatch(long id);

}

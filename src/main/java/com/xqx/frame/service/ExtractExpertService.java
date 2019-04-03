package com.xqx.frame.service;

import java.util.Map;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;

/**
 * 专家抽取Service
 * 
 * @author DELL
 *
 */
public interface ExtractExpertService {

	/**
	 * 专家抽取
	 * @param id 
	 * 
	 * @param id
	 * @param extractPepleNum
	 * @param extractType
	 * @return
	 */
	public Map<String, Object> randomExtractExpert(Long id, Integer extractPepleNum);

	/**
	 * 重抽专家
	 * @param batchId 
	 * 
	 * @param expertIds
	 * @param expertFlag
	 * @return
	 */
	public TExpert reelectExpert(Long batchId, Long expertId, String expertIds, Integer expertFlag);
	
	/**
	 * 确认抽取结果
	 * 
	 * @param id
	 */
	public void confirmExtract(Long id)  throws ParameterCheckException;
	
	/**
	 * 修改批次状态
	 * 
	 * @param batchId
	 * @param status
	 * @return
	 */
	public boolean updateBatchStatus(Long batchId, Integer status);

	
	public Map<String, Object> extractStatistics(String type);

	/**
	 * 修改抽取专家通知状态
	 * 
	 * @param id
	 * @param expertIds
	 * @return
	 */
	public boolean updateExtractExpertNoteStatus(Long id, String expertIds);

	/**
	 * 验证抽取的专家是否大于专家库数量
	 * 
	 * @param extractPepleNum
	 * @return
	 */
	public Map<String, Object> validateExtractPepleNum(int extractPepleNum);

}

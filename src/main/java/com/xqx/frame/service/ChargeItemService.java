package com.xqx.frame.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TGrade;



public interface ChargeItemService {

	/**
	 *  保存收费项目
	 */
	public String saveChargeItem(TChargeItem chargeitem);
	
	
	/**
	 * 根据ID查找收费项
	 * 
	 */

	public TChargeItem findChargeitemById(Long id);
	
	
	
	
	/**
	 * 根据收费项名称查找
	 */
	
	public 	List<TChargeItem> findChargeitemByName(String name);
	
	/**
	 * 删除收费项
	 */
	
	public void deleteChargeitem(Long id);
	
	
	public Page<TChargeItem> findAll(final String name,Pageable pageable);



}

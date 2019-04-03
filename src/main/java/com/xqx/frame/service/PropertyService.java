package com.xqx.frame.service;

import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TProperty;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {

	String save(TProperty entity);
	
	TProperty findOne(Long id);

	public List<TProperty> getByFidAndName(Long fid,String fname);
	
	public List<TProperty> getByFid(Long fid);
	
	public List<TProperty> getByFidAndRemak(Long fid,String fRemak);
	
	public TProperty getById(Long id);
	public Page<TProperty> findAll(final String fname,Pageable pageable);
	List<TProperty> findByLikeFid(Long typeId);


    /**
     * 获取配置证书开头列表
     * @param fMemo
     * @param fId
     * @return
     */
	List<TProperty> configList(String fMemo,Long fId);

	public List<TProperty> getByRemark(String string);
	public void delete(long id);
}

package com.xqx.frame.service;

import java.util.List;

import com.xqx.frame.model.TChildren;


public interface TPayedInfoService {
	public List<String> findTPayedInfoByid(Long id);

	public TChildren findPayedInfoBychildrenId(Long childId);
}

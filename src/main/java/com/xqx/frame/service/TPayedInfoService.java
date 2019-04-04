package com.xqx.frame.service;

import java.util.List;

import com.xqx.frame.model.TPayedInfo;

public interface TPayedInfoService {
	public List<TPayedInfo> findTPayedInfoByid(Long id);
	public List<TPayedInfo> findTPayedInfoByupdateUserID(Long id);
}

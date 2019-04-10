package com.xqx.frame.service;

import java.util.List;

import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TPayedInfo;


public interface TPayedInfoService {
	public List<PlayQueryVO> findTPayedInfoByid(Long id);

	public TChildren findPayedInfoBychildrenId(Long childId);
	
	
	public List<TPayedInfo> getTPayedInfo(Long extractPepleNum, Long childId) ;

	List<PlayQueryVO> getTPayedInfo(int extractPepleNum, Long childId);
}

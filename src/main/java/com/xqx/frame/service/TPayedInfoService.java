package com.xqx.frame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TPayedInfo;


public interface TPayedInfoService {
	public List<PlayQueryVO> findTPayedInfoByid(Long id);

	public TChildren findPayedInfoBychildrenId(Long childId);
	public Page<TPayedInfo> findAll(final String name,Pageable pageable);
	public List<TPayedInfo> getTPayedInfo(Long extractPepleNum, Long childId) ;
	public void deleteTPayedInfo(long id);
	List<PlayQueryVO> getTPayedInfo(int extractPepleNum, Long childId);
}

package com.xqx.frame.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.form.PlayDeaFinfo;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TPayedInfo;


public interface TPayedInfoService {
	public List<PlayQueryVO> findTPayedInfoByid(Long id);

	public TChildren findPayedInfoBychildrenId(Long childId);
	public Page<TPayedInfo> findAll(final String name,final String beginTime, final String endTime,Pageable pageable);
	public List<TPayedInfo> findAll(Payetyped payetypeds,Date stardate,Date enddate);
	public List<TPayedInfo> getTPayedInfo(Long extractPepleNum, Long childId) ;
	public void deleteTPayedInfo(long id);
	List<PlayQueryVO> getTPayedInfo(int extractPepleNum, Long childId);
/*	public List<Map<String, Object>> getTPayeddeafultInfo(Long PId);*/
	public PlayDeaFinfo  getTPayeddeafultInfo(Long PId);
}

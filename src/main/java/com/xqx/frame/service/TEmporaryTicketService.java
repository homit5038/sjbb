package com.xqx.frame.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TEmporaryTicket;
import com.xqx.frame.model.TGrade;



public interface TEmporaryTicketService {

	/**
	 * 根据模板ID名称查找
	 */
	
	public 	List<TEmporaryTicket> findTEmporaryTicketByTemplateid(Long templateid);
	
	/**
	 * 删除收费项
	 * @throws Exception 
	 */
	
	public void deleteTEmporaryTicketBytemplateid(Long templateid);
	
/*	public void deleteTickettempById(long id);*/




}

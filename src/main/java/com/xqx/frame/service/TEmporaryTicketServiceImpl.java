package com.xqx.frame.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.xqx.frame.dao.TEmporaryTicketDao;
import com.xqx.frame.dao.TIcketTemplateDao;

import com.xqx.frame.model.TEmporaryTicket;

import com.xqx.frame.model.TIcketTemplate;





@Service
public class TEmporaryTicketServiceImpl implements TEmporaryTicketService {
	
	@Autowired
	TEmporaryTicketDao temporaryTicketDao;
	@Autowired
	TIcketTemplateDao ticketTemplateDao;

	@Autowired
	TIcketTemplateService ticketTemplateService;
	@Override
	public List<TEmporaryTicket>findTEmporaryTicketByTemplateid(Long templateid) {
		return temporaryTicketDao.findTEmporaryTicketByTemplateid(templateid);
	}
	
	
	@Override
	public void deleteTEmporaryTicketBytemplateid(Long id) {
		try {
			temporaryTicketDao.deleteByTEmporaryTicketTemplatesId(id);
			ticketTemplateService.deleteTickettempById(id);
		} catch (Exception e) {
			throw e;
		}
	}

/*	@Override
	public void deleteTEmporaryTicketBytemplateid(Long templateid) {
		TEmporaryTicket chargeItem=temporaryTicketDao.findOne(templateid);
		temporaryTicketDao.delete(chargeItem);
        

	}*/

	

	
	
	
}

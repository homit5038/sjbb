package com.xqx.frame.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xqx.frame.dao.TIcketTemplateDao;

@Service
public class TIcketTemplateServiceImpl implements TIcketTemplateService {

	@Autowired
	TIcketTemplateDao ticketTemplateDao;

	

	@Transactional
	@Override
	public  void deleteTickettempById(long id) {
		ticketTemplateDao.delete(id);
	}
}

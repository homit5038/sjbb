package com.xqx.frame.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TExpertBatch;
import com.xqx.frame.model.TPayedInfo;
@Service
public class TPayedInfoServiceImpl implements TPayedInfoService {
	@Autowired
	TPayedInfoDao payedinfoDao;
	@Autowired
	TChildrenDao childrenDao;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<String> findTPayedInfoByid(Long childId) {
	
		return  payedinfoDao.listFCompanyNameByFOriginalCompanyID(childId);
	}
	
	// 获取session
	public Session getSession() {
		Session session = (Session) entityManager.getDelegate();

		if (session.isOpen() == false) {
			session = session.getSessionFactory().openSession();
		}

		return session;
	}
	
	
	// 通过传入sql获取专家
	@SuppressWarnings("unchecked")
	public List<TPayedInfo> findTExpert(String sql) {
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TPayedInfo.class);
		return query.list();
	}
	
	
	@Override
	public TChildren findPayedInfoBychildrenId(Long childId) {
	
		TChildren payed = childrenDao.findOne(childId);
		//只取有效的专家
		String sql = " select e.chargerealpay,e.chargereturn,e.chargeshouldpay,e.flowCode,e.remarks,e.timeb,e.childId,e.childId"
				+ " from TPayedInfo e join TChildren eb on eb.id = e.childId where  "
				+ " and eb.batchId = "+ childId
				+ " order by flag desc ";
		List<TPayedInfo> payedinfo = findTExpert(sql);
		if(payedinfo != null && payedinfo.size() > 0){
			payed.setPayinfo(payedinfo);
		}
		return payed;
	}
	

}

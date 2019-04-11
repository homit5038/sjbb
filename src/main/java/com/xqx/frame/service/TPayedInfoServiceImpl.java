package com.xqx.frame.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TChildren;

import com.xqx.frame.model.TPayedInfo;
@Service
public class TPayedInfoServiceImpl implements TPayedInfoService {
	@Autowired
	TPayedInfoDao payedinfoDao;
	@Autowired
	TChildrenDao childrenDao;
	@Autowired 
	EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	private EntityManager entityManager;
	private Object BigDecimal;

	@Override
	public List<PlayQueryVO> findTPayedInfoByid(Long childId) {
	
		return  payedinfoDao.findPayedInfoBychildId(childId);
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
	public List<PlayQueryVO> findTExpert(String sql) {
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TPayedInfo.class);
	
		return query.list();
	}

	@Override
	public Page<TPayedInfo> findAll(final String name,Pageable pageable) {
		
		return payedinfoDao.findAll(new Specification<TPayedInfo>() {
			
			@Override
			public Predicate toPredicate(Root<TPayedInfo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("ItemName"), name));  
                }  
                
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}
	
	
	@Override
	public List<PlayQueryVO> getTPayedInfo(int extractPepleNum, Long childId) {
		
		String sql = "select TOP "+extractPepleNum+" t.* from TPayedInfo t where t.childId = " + childId;
		sql += " order by NEWID() ";
		javax.persistence.Query query = entityManagerFactory.createEntityManager().createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> result = query.getResultList();
	
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result;
		
		List<PlayQueryVO>  ciVo = new ArrayList<PlayQueryVO>();
		if (rows != null && rows.size() > 0) {
			for (int i = 0; i < rows.size(); i++) {
				PlayQueryVO ci = new PlayQueryVO();
				
				ci.setPaytype(rows.get(i).get("Paytype") == null ? null : rows.get(i).get("Paytype").toString());
				ci.setChargerealpay(rows.get(i).get("chargerealpay") == null ? null : rows.get(i).get("chargerealpay").toString());
				ci.setPaytype(rows.get(i).get("paytype") == null ? null : rows.get(i).get("paytype").toString());
				ci.setFlowCode(rows.get(i).get("flowCode") == null ? null : rows.get(i).get("flowCode").toString());
				
				ci.setChargConnection(rows.get(i).get("chargConnection") == null ? null : rows.get(i).get("chargConnection").toString());
				ci.setChargereturn(rows.get(i).get("chargereturn") == null ? null : rows.get(i).get("chargereturn").toString());
				ci.setPayableDsate(rows.get(i).get("payableDsate") == null ? null : rows.get(i).get("payableDsate").toString());
				ci.setTimeb(rows.get(i).get("timeb") == null ? null : rows.get(i).get("timeb").toString());
			   
				ci.setChargeshouldpay(rows.get(i).get("chargeshouldpay") == null ? null : rows.get(i).get("chargeshouldpay").toString());
				
				ciVo.add(ci);
			}
		}
		
		
		return ciVo;
	}
	
	
	
	@Override
	public TChildren findPayedInfoBychildrenId(Long childId) {
	
		TChildren payed = childrenDao.findOne(childId);
		//只取有效的专家
		String sql = " select e.chargerealpay,e.chargereturn,e.chargeshouldpay,e.flowCode,e.remarks,e.timeb,e.childId,e.childId"
				+ " from TPayedInfo e join TChildren eb on eb.id = e.childId where  "
				+ " and eb.batchId = "+ childId
				+ " order by flag desc ";
		List<PlayQueryVO> payedinfo = findTExpert(sql);
/*		if(payedinfo != null && payedinfo.size() > 0){
			payed.setPayinfo(payedinfo);
		}*/
		return payed;
	}

	@Override
	public List<TPayedInfo> getTPayedInfo(Long extractPepleNum, Long childId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void deleteTPayedInfo(long id) {
		TPayedInfo payedInfo = payedinfoDao.findOne(id);
		payedinfoDao.delete(payedInfo);

	}

}

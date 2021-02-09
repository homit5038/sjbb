package com.xqx.frame.service;
import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.form.PlayDeaFinfo;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TPayedInfo;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
	public Page<TPayedInfo> findAll(final String name,final String beginTime,final String endTime,Pageable pageable) {
		
		return payedinfoDao.findAll(new Specification<TPayedInfo>() {
			
			@Override
			public Predicate toPredicate(Root<TPayedInfo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.<TChildren>get("children").<Long>get("childName"), name));  
                }  
              
  
                if (!StringUtils.isEmpty(beginTime)
      						&& !StringUtils.isEmpty(endTime)) {
      					SimpleDateFormat sdf = new SimpleDateFormat(
      							"yyyy-MM-dd HH:mm:ss");
      					try {
      						predicates.add(cb.between(root.<Date> get("createTime"),
      										sdf.parse(beginTime+" 00:00:00"),
      										sdf.parse(endTime+" 23:59:59")));
      					} catch (ParseException e) {
      						e.printStackTrace();
      					}
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

				ci.setId(rows.get(i).get("id") == null ? 0 :Integer.parseInt(rows.get(i).get("id").toString()));
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
	public PlayDeaFinfo getTPayeddeafultInfo(Long PId) {
		return payedinfoDao.readPayedInfo(PId);
		
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
	public List<TPayedInfo> findAll(Payetyped paytype,Date stardate,Date enddate) {
	      
		
		return payedinfoDao.payedInfoStatistiss(paytype,stardate,enddate);
		
		
		
		
	/*return payedinfoDao.findAll(new Specification<TPayedInfo>() {
			
			@Override
			public Predicate toPredicate(Root<TPayedInfo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(paytype)){ 
                	predicates.add(cb.equal(
							root.<Payetyped> get("paytype"),
							Payetyped.valueOf(paytype)));	
                  
                }  
                
                
                if (!StringUtils.isEmpty(date)
      						&& !StringUtils.isEmpty(date)) {
      					SimpleDateFormat sdf = new SimpleDateFormat(
      							"yyyy-MM-dd HH:mm:ss");
      					try {
      						 //predicates.add(cb.equal(root.<Date>get("createTime"), sdf.parse(date+" 00:00:00")));
      						 
      						predicates.add(cb.between(root.<Date> get("createTime"),
    								sdf.parse(date+" 00:00:00"),
    								sdf.parse(date+" 23:59:59")));
      						 
      						
      					} catch (ParseException e) {
      						e.printStackTrace();
      					} 
      	                
      					
      					
      				}
                
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		});*/
		
	}
	
	
	
	@Override
	public List<TPayedInfo> getTPayedInfo(Long extractPepleNum, Long childId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public  void deleteTPayedInfo(long id) {
		//TPayedInfo payedInfo = payedinfoDao.findOne(id);
		
		 payedinfoDao.delete(id);
	}

}

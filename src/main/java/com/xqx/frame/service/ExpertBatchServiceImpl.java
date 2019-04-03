package com.xqx.frame.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.ExpertBatchDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TExpertBatch;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.util.PubUtil;

@Service
public class ExpertBatchServiceImpl implements ExpertBatchService {
	@Autowired
	private ExpertBatchDao expertBatchDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	HttpServletRequest request;
	
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
	public List<TExpert> findTExpert(String sql) {
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TExpert.class);
		return query.list();
	}

	@Override
	public Map<String, Object> findBatch() {
		Map<String, Object> map = new HashMap<String, Object>();
		String batchName = request.getParameter("batchName");
		map.put("batchName", batchName);
		String batchId = request.getParameter("batchId");
		map.put("batchId", batchId);
		String expertName = request.getParameter("expertName");
		map.put("expertName", expertName);
		String pgjg = request.getParameter("pgjg");
		map.put("pgjg", pgjg);
		String batchStatus = request.getParameter("batchStatus");
		map.put("batchStatus", batchStatus);
		String beginTime = request.getParameter("beginTime");
		map.put("beginTime", beginTime);
		String endTime = request.getParameter("endTime");
		map.put("endTime", endTime);
		StringBuffer sql = new StringBuffer("select distinct eb.* from TExpertBatch eb "
				+ "left join Expert_Batch_dz t on eb.id=t.batchID "
				+ "left join TExpert e on t.expertID=e.id where 1=1 and eb.availability=1 ");
		if (StringUtils.hasText(batchId)) {
			sql.append("and eb.id = '" + batchId + "' ");
		}
		if (StringUtils.hasText(batchName)) {
			sql.append("and eb.batchName = '" + batchName + "' ");
		}
		if (StringUtils.hasText(batchStatus)&&!"all".equals(batchStatus)) {
			if("-1".equals(batchStatus)){
				sql.append("and eb.batchStatus <> 2");
			} else {
				sql.append("and eb.batchStatus = '" + batchStatus + "'");
			}
		}
		if (StringUtils.hasText(expertName)) {
			sql.append("and e.expertName = '" + expertName + "' ");
			sql.append("and t.extractStatus = 1 ");
		}
		if (StringUtils.hasText(pgjg)) {
			sql.append("and e.assessmentStructure = '" + pgjg + "' ");
			sql.append("and t.extractStatus = 1 " );
		}
		if (StringUtils.hasText(beginTime)) {
			sql.append("and eb.createTime >= '" + beginTime + "' ");
		}
		if (StringUtils.hasText(endTime)) {
			sql.append("and eb.createTime <= '" + endTime + "' ");
		}
		sql.append(" order by updateLastTime desc ");
		
		Session session = getSession();
		Query query = session.createSQLQuery(sql.toString());
		// 记录总数
		int total = query.list().size();
		// 每页的记录数
		Integer size = Integer.MAX_VALUE;
		String pageSize = request.getParameter("size");
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = Config.getString("pageSize");
		}
		if (StringUtils.hasText(pageSize)) {
			size = Integer.valueOf(pageSize);
		}
		// 页码
		Integer pageNum = 0;
		String page = request.getParameter("page");
		if (StringUtils.hasText(page)) {
			pageNum = Integer.valueOf(page);
		}
		query.setFirstResult(pageNum * size);
		query.setMaxResults(size);
		List<?> list = query.setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP).list();
		map.put("data", list);
		// 当前记录数
		map.put("currentSize", list.size());
		// 总的记录数
		map.put("total", total);
		map.put("pageNum", pageNum);
		// 总的页数
		map.put("totalPage", total % size == 0 ? total / size : total / size
				+ 1);
		map.put("size", size);

		return map;
	}

	@Override
	public TExpertBatch findExpertBatchById(long id) {
		TExpertBatch batch = expertBatchDao.findOne(id);
		//只取有效的专家
		String sql = " select e.expertTypeID,e.availability,e.id,e.expertName,e.assessmentStructure,e.phoneNum,e.createTime,e.expertEmail,e.flag,"
				+ " e.createUserID,e.updateUserID,e.creatorIp,e.updateLastTime,e.lastUpdaterIp,eb.noteStatus noteStatus "
				+ " from TExpert e join Expert_Batch_dz eb on e.id = eb.expertID where eb.extractStatus = 1  "
				+ " and eb.batchId = "+ id
				+ " order by flag desc ";
		List<TExpert> experts = findTExpert(sql);
		if(experts != null && experts.size() > 0){
			batch.setExperts(experts);
		}
		return batch;
	}

	@Override
	public void saveExpertBatch(TExpertBatch batch) throws ParameterCheckException {
		if (batch != null) {
			if(batch.getId() != null){
				batch.audit(false);
				this.setBatchName(batch);
			} else {
				batch.audit(true);
				batch.setAvailability(1);
				batch.setBatchStatus(0);
				this.setBatchName(batch);
			}
			expertBatchDao.save(batch);
		}
	}
	
	//设置空批次名称
	private void setBatchName(TExpertBatch batch){
		String batchName = batch.getBatchName();
		if(StringUtils.isEmpty(batchName)){
			TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
			String dateStr = PubUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分ss秒");
			batch.setBatchName(user.getName()+" "+dateStr);
		}
	}

	@Override
	public void deleteExpertBatch(long id) {
		TExpertBatch batch = expertBatchDao.findOne(id);
		batch.audit(false);
		batch.setAvailability(0);
		expertBatchDao.save(batch);
	}
}

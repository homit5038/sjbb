package com.xqx.frame.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xqx.frame.dao.ExpertBatchDao;
import com.xqx.frame.dao.ExtractExpertDao;
import com.xqx.frame.dao.TExpertDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TExpertBatch;

/**
 * 专家抽取service
 *
 */
@Service
public class ExtractExpertServiceImpl implements ExtractExpertService {
	@Autowired
	private ExtractExpertDao extractExpertDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ExpertBatchDao expertBatchDao;
	
	@Autowired
	private TExpertDao expertDao;
	
	@Autowired
	private HttpServletRequest request;
	
	//获取session
	public Session getSession(){
		Session session = (Session) entityManager.getDelegate();
		
		if (session.isOpen() == false) {
			session = session.getSessionFactory().openSession();
		}

		return session;
	}
	
	//通过传入sql获取专家
	@SuppressWarnings("unchecked")
	public List<TExpert> findTExpert(String sql){
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TExpert.class);
		return query.list();
	}
	
	/**
	 * 全部抽取
	 */
	@Override
	public Map<String, Object> randomExtractExpert(Long batchId, Integer extractPepleNum) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<TExpert> experts = new ArrayList<TExpert>();
		
		if(extractPepleNum == null || extractPepleNum < 0 || extractPepleNum > 99){
			result.put("msg", "抽取人数只能为1到99");
		} else {
			//获取有效专家数量
			int expertCount = extractExpertDao.getExpertCount();
			
			if(extractPepleNum <= expertCount ){
				//抽取全部
				experts = this.extractAll(extractPepleNum);
				if(experts.size() > 0){
					//保存抽取结果
					this.saveExtractExpert(batchId,experts);
					//保存批次已经抽取
					this.updateBatchStatus(batchId, 1);
				}
				result.put("msg", true);
			} else {
				result.put("msg", "抽取数量："+extractPepleNum+"，专家数量："+expertCount+"，抽取人数比专家数量多，请修改抽取人数才重抽。");
			}
		}
		result.put("experts", experts);
		return result;
	}

	/**
	 * 通过抽取数目抽取专家
	 * 
	 * @param extractPepleNum
	 * @return
	 */
	private List<TExpert> extractAll(Integer extractPepleNum) {
		List<TExpert> experts = new ArrayList<TExpert>();
		
		List<TExpert> optionalExperts = new ArrayList<TExpert>();
		
		//获取一位必选专家
		List<TExpert> equiredExpert = this.getExpert(1, 1, null);
		
		if(equiredExpert != null && equiredExpert.size() > 0){
			optionalExperts = this.getExpert(extractPepleNum - 1, 0, null);
			experts.addAll(equiredExpert);
		} else {
			optionalExperts = this.getExpert(extractPepleNum, 0 , null);
		}
		experts.addAll(optionalExperts);
		
		return experts;
	}

	/**
	 * 抽取专家
	 * 
	 * @param extractPepleNum 抽取的人数
	 * @param expertFlag 专家类型 0：可选   1：必选 -1：不可选
	 * @param expertIds 重抽排除已经存在的专家id
	 * @return
	 */
	private List<TExpert> getExpert(int extractPepleNum, int expertFlag, String expertIds) {
		
		String sql = "select TOP "+extractPepleNum+" t.* from TExpert t where availability = 1  and  t.flag = " + expertFlag;
		
		if(this.checkId(expertIds)){
			expertIds = expertIds.substring(0, expertIds.length() - 1);
			sql +=" and t.id not in(" + expertIds + ")";
		}
		
		sql += " order by NEWID() ";
		
		return this.findTExpert(sql);
	}

	/**
	 * 重抽
	 */
	@Override
	public TExpert reelectExpert(Long batchId, Long expertId, String expertIds,  Integer expertFlag) {

		List<TExpert> experts  = this.getExpert(1, expertFlag, expertIds);
		
		if (experts != null && experts.size() > 0) {
			// 保存抽取的专家
			this.saveExtractExpert(batchId, experts);
			// 设置以前抽取的专家状态为无效
			this.updateOldExpactExpert(batchId, expertId, 0);
		}

		return (experts != null && experts.size() > 0) ? experts.get(0) : null;
	}
	
	/**
	 * 检查重抽时排除已有的专家ID，只能为数字
	 * 
	 * @param expertIds
	 * @return
	 */
	private boolean checkId(String expertIds){
		boolean flag = true;
		if (!StringUtils.isEmpty(expertIds)) {
			String[] idsArr = expertIds.split(",");
			// 避免sql注入
			for (String id : idsArr) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (!pattern.matcher(id).matches()) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 设置以前抽取的专家状态为无效
	 * 
	 * @param batchId
	 * @param expertId
	 */
	private void updateOldExpactExpert(Long batchId, Long expertId, Integer status) {
		Session session = this.getSession();
		String sql = "update Expert_Batch_dz set extractStatus = "+status+" where batchID = " + batchId + " and expertID = " +expertId;
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	/**
	 * 保存抽取结果
	 * 
	 * @param batchId
	 * @param experts
	 */
	@Transactional
	private void saveExtractExpert(Long batchId, List<TExpert> experts) {
		Session session = this.getSession();
		String sql = "insert into Expert_Batch_dz(batchID, expertID,extractStatus) values(";
		for (TExpert expert : experts) {
			if(this.confirmExtractHasOldExpert(batchId,expert.getId())){
				//老数据直接修改为有效
				this.updateOldExpactExpert(batchId, expert.getId(), 1);
			} else {
				StringBuffer sd = new StringBuffer(sql);
				sd.append(batchId + " , " + expert.getId() + ",1)");
				SQLQuery query = session.createSQLQuery(sd.toString());
				query.executeUpdate();
			}
		}
	}
	
	/**
	 * 判断该批次是不是已经抽取过改专家 
	 * 
	 * @param batchId
	 * @param expertId
	 * @return
	 */
	private boolean confirmExtractHasOldExpert(Long batchId, Long expertId) {
		Session session = this.getSession();
		
		String sql = "select count(1) from Expert_Batch_dz t where batchID = " + batchId+" and expertId = " + expertId;
		
		Query query = session.createSQLQuery(sql);
		List<?> list = query.list();
		if(list != null && (Integer)list.get(0) > 0){
			return true;
		} 
		return false;
	}

	@Override
	public void confirmExtract(Long batchId) throws ParameterCheckException {
		Session session = this.getSession();
		String sql = "select count(1) from Expert_Batch_dz where batchID = " + batchId;
		
		Query query = session.createSQLQuery(sql);
		//先判断该批次是否存在抽取专家
		List<?> list = query.list();
		if(list != null && list.size() > 0){
		    this.updateBatchStatus(batchId, 2);
		} else {
			throw new ParameterCheckException("该批次没有抽取结果");
		}
	}
	
	/**
	 * 修改批次状态
	 * 
	 * @param batchId
	 * @param status
	 * @return
	 */
	@Override
	public boolean updateBatchStatus(Long batchId, Integer status){
		//修改抽取状态
		TExpertBatch expertBatch = expertBatchDao.findOne(batchId);
		expertBatch.setBatchStatus(status);
		expertBatchDao.save(expertBatch);
		
		return true;
	}
	
	@Transactional
	@Override
	public boolean updateExtractExpertNoteStatus(Long batchId, String expertIds) {
		String[] idsArr = expertIds.split(",");
		Session session = this.getSession();
		
		String sql = "update Expert_Batch_dz set noteStatus = 1 where extractStatus = 1 and batchID = " + batchId ;
		if(idsArr.length == 1){
			sql += " and expertID = " +expertIds;
		}
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		return true;
	}
	
	/**
	 * 统计
	 */
	@Override
	public Map<String, Object> extractStatistics(String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		String beginTime = request.getParameter("beginTime");
		map.put("beginTime", beginTime);
		String endTime = request.getParameter("endTime");
		map.put("endTime", endTime);
		if("zj".equals(type)){
			sql.append("select e.id,e.expertName xData,COUNT(t.expertID) num from Expert_Batch_dz t left join TExpert e "
					+ "on t.expertID = e.id left join TExpertBatch eb on eb.id=t.batchID where t.extractStatus = 1 ");
		}else if("jg".equals(type)){
			sql.append("select e.assessmentStructure xData,COUNT(t.expertID) num from Expert_Batch_dz t left join TExpert e "
					+ "on t.expertID=e.id left join TExpertBatch eb on eb.id=t.batchID where t.extractStatus=1 ");
		}

		if (StringUtils.hasText(beginTime)) {
			sql.append("and eb.createTime >= '" + beginTime + " 00:00:00' ");
		}
		if (StringUtils.hasText(endTime)) {
			sql.append("and eb.createTime <= '" + endTime + " 23:59:59' ");
		}
		
		Session session = getSession();
		if("zj".equals(type)){
			sql.append("group by e.id,e.expertName ");
		}else if("jg".equals(type)){
			sql.append("group by e.assessmentStructure ");
		}
		
		Query query = session.createSQLQuery(sql.toString());
		List<?> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		JSONArray xArray = new JSONArray();
		JSONArray yArray = new JSONArray();
		for (int i=0;i<list.size();i++){
			Object obj = list.get(i);
			Map<?, ?> map1 = (Map<?, ?>) obj;
			xArray.add(map1.get("xData"));
			yArray.add(i, map1.get("num"));
		}
		map.put("xArray", xArray);
		map.put("yArray", yArray);
		return map;
	}

	@Override
	public Map<String, Object> validateExtractPepleNum(int extractPepleNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = expertDao.getExpertCount();
		String msg = "NO";
		if(count >= extractPepleNum){
			msg = "OK";
		}
		
		map.put("msg", msg);
		map.put("count", count);
		
		return map;
	}

}

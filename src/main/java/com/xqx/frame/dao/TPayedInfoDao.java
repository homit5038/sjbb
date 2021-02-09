package com.xqx.frame.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.list.LazyList;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.form.PlayDeaFinfo;
import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TPayedInfo;

@Repository
public interface TPayedInfoDao extends JpaRepository<TPayedInfo, Long>  {

	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */


	/*@Query(value="select top 10 from TPayedInfo ci where ci.updateUserID :childId")
	List<TPayedInfo> findPayedInfoBychildId(@Param("childId")Long childId);
	*/
	
/*	@Query(value="select u.id, u.paytype, u.flowCode,u.chargereturn from TPayedInfo u where u.id = ?1",nativeQuery=true)
	TPayedInfo readPayedInfo(@Param("cid")Long cid);
	@Query(value = "select new map(u.userName, ui.name, ui.gender, ui.description) from UserInfo ui, User u where u.id = ui.userId")
	public List<Map<String, Object>> getCustomField();*/
	
	
	//@Query(value = "select new map(u.id, u.paytype,u.flowCode,u.chargereturn ) from TPayedInfo u where u.id = ?1")
	//TPayedInfo readPayedInfo(@Param("cid")Long cid);
		/*Object<Map<String, Object>> readPayedInfo(@Param("cid")Long cid);*/
	//Map<String, Object> readPayedInfo(@Param("cid")Long cid);
	
	@Query(value = "select new com.xqx.frame.form.PlayDeaFinfo(ui.id,ui.paytype,ui.flowCode, ui.chargereturn,"
			+ "ui.chargerealpay,ui.chargeshouldpay,ui.children,ui.remarks,ui.payableDsate) from TPayedInfo ui where ui.id = ?1")
	public PlayDeaFinfo readPayedInfo(@Param("cid")Long cid);
	
	@Query(value="select a.* from TPayedInfo a where  a.childId = ?1",nativeQuery=true)
	List<PlayQueryVO> findPayedInfoBychildId(@Param("childId")Long childId);

	
	@Query("from TPayedInfo ci where ci.updateUserID = ?1 ")
	TPayedInfo findPayedInfoByUserID(@Param("updateUserID") Long UserID);
	
	
	@Query("select s from TPayedInfo s where s.paytype =? or createTime is not null and createTime >= ? and createTime <= ?")
	public List<TPayedInfo> payedInfoStatistiss(@Param("paytype") Payetyped paytype,Date stardate,Date enddate);
	
	
	@Query("select count(s.children.id) from TPayedInfo s where s.paytype =? or s.createTime is not null and s.createTime >= ? and s.createTime <= ?  group by s.children.id")
	public  List<String> payedInfoStaticonut(@Param("paytype") Payetyped paytype,Date stardate,Date enddate);
	
	@Query("select sum(s.chargerealpay) from TPayedInfo s where s.paytype =? or createTime is not null and createTime >= ? and createTime <= ?")
	public Long payedInfoStatisum(@Param("paytype") Payetyped paytype,Date stardate,Date enddate);
	
	@Query("select s from TPayedInfo s where s.paytype =?1 or s.createTime=?2")
	public List<TPayedInfo> payedInfoStatistics(@Param("paytype") Payetyped paytype,String date);
	
	
	@Query("select s from TPayedInfo s where s.children.id =?1 order by createTime desc")
	public List<TPayedInfo> payedInfoStatiConut(Long children);
	
	//@Query("select count(s.id) from TPayedInfo s where s.paytype = ?1 or s.createTime=?2")
	//public long payedInfoStatistics(String tpye,String date);
	
	List<TPayedInfo> findAll(Specification<TPayedInfo> spec);
	Page<TPayedInfo> findAll(Specification<TPayedInfo> spec,Pageable page);

}

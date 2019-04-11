package com.xqx.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.form.PlayQueryVO;
import com.xqx.frame.model.TChargeItem;
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
	

	@Query(value="select a.* from TPayedInfo a where  a.childId = ?1",nativeQuery=true)
	List<PlayQueryVO> findPayedInfoBychildId(@Param("childId")Long childId);

	
	@Query("from TPayedInfo ci where ci.updateUserID = ?1 ")
	TPayedInfo findPayedInfoByUserID(@Param("updateUserID") Long UserID);
	
	
	Page<TPayedInfo> findAll(Specification<TPayedInfo> spec,Pageable page);

}

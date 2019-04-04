package com.xqx.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.xqx.frame.model.TPayedInfo;

@Repository
public interface TPayedInfoDao extends JpaRepository<TPayedInfo, Long>  {

	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TPayedInfo where childId = ?1")
	List<TPayedInfo> findPayedInfoBychildId(Long childId);
	
	
	@Query("from TPayedInfo where updateUserID = ?1")
	List<TPayedInfo> findPayedInfoByUserID(@Param("updateUserID") Long UserID);
	
}

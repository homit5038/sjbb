package com.xqx.frame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TExpert;

/**
 * 专家抽取Dao
 * 
 * @author
 */
@Repository
public interface ExtractExpertDao extends JpaRepository<TExpert, Long> {
	
	/**
	 * 获取必选专家数量
	 * 
	 * @return
	 */
	@Query("select count(id) from TExpert where availability = 1 and flag = 1")
	public int getEquiredExpertCount();
	
	/**
	 * 获取有效专家数量
	 * 
	 * @return
	 */
	@Query("select count(id) from TExpert where availability = 1 and flag <> -1")
	public int getExpertCount();

}

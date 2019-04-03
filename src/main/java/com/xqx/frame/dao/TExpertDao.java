package com.xqx.frame.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TExpert;

@Repository
public interface TExpertDao extends JpaRepository<TExpert, Long> {

	/**
	 * 多条件查询
	 * @param spec
	 * @param page
	 * @return
	 */
	Page<TExpert> findAll(Specification<TExpert> spec,Pageable page);

	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TExpert where expertName = ?1")
	List<TExpert> findByName(String name);
	

	
	/**
	 * 根据评估机构查询专家信息
	 * @param assessmentStructure
	 * @return
	 */
	@Query("from TExpert where assessmentStructure = ?1")
	List<TExpert> findByAssessmentStructure(String assessmentStructure);
	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TExpert where expertName = ?1 and assessmentStructure = ?2 and expertEmail = ?3 and phoneNum = ?4 and availability = 1")
	List<TExpert> findByAll(String name,String pgjg,String email,String phone);

	/**
	 * 验证抽取的专家中有多少个必选
	 * 
	 * @param expertIds
	 * @return
	 */
	@Query("select count(1) from TExpert where flag = 1 and availability = 1 and id in :expertIds")
	int getEquireCount(@Param("expertIds")Set<Long> expertIds);
	
	/**
	 * 获取数据库有多少可选专家
	 * @return
	 */
	@Query("select count(1) from TExpert where flag <> -1 and availability = 1")
	int getExpertCount();
	
}

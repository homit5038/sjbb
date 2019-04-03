package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TChildren;



@Repository
public interface TChildrenDao extends JpaRepository<TChildren, Long> {
	/**
	 * 多条件查询
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<TChildren> findAll(Specification<TChildren> spec,Pageable page);
	
	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param card
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TChildren where childName = ?1 and childSchoolId = ?2 and childIdcardNumber = ?3 and attendanceIdcardNumber = ?4")
	List<TChildren> findByAll(String childName,String childSchoolId,String childIdcardNumber,String attendanceIdcardNumber);

	
	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TChildren where childName = ?1")
	List<TChildren> findBychildrenname(String name);

	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TChildren where childName = ?1")
	List<TChildren> findByAll(String name);

	
}

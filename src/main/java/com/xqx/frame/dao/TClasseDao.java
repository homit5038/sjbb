package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TClasses;


@Repository
public interface TClasseDao extends JpaRepository<TClasses, Long> {

	/**
	 * 多条件查询
	 * @param spec
	 * @param page
	 * @return
	 */
	Page<TClasses> findAll(Specification<TClasses> spec,Pageable page);

	
	
	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TClasses")
	List<TClasses> findAll();
	

	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TClasses where classesname = ?1")
	List<TClasses> findByclassesname(String name);

	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TClasses where classesname = ?1")
	List<TClasses> findByAll(String name);


	
}

package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.form.PageableQueryObject;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TExpert;

@Repository
public interface TEmployeDao extends JpaRepository<TEmploye, Long> {

	/**
	 * 多条件查询
	 * @param spec
	 * @param pageable
	 * @return
	 */

	//Page<TEmploye> findAll(Specification<TEmploye> spec,PageableQueryObject pageable);
	Page<TEmploye> findAll(Specification<TEmploye> spec,Pageable page);
	
	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param card
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TEmploye where employeName = ?1 and idcardNumber = ?2 and employeEmail = ?3 and phoneNum = ?4")
	List<TEmploye> findByAll(String name,String card,String email,String phone);

	
	
	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TEmploye where employeName = ?1")
	List<TEmploye> findByName(String name);

	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TEmploye where employeName = ?1")
	List<TEmploye> findByAll(String name);

	
}

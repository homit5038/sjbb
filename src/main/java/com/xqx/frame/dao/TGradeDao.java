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
import com.xqx.frame.model.TGrade;

@Repository
public interface TGradeDao extends JpaRepository<TGrade, Long> {

	/**
	 * 多条件查询
	 * @param spec
	 * @param page
	 * @return
	 */
	Page<TGrade> findAll(Specification<TGrade> spec,Pageable page);

	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TGrade where gradename = ?1")
	List<TGrade> findBygradename(String name);

	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TGrade where gradename = ?1")
	List<TGrade> findByAll(String name);


	
}

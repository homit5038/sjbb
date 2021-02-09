package com.xqx.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	//@Query("select s from  TClasses  s where s.grade.id = ?1")
	//List<TClasses> findByclassesByGradeid(Long gradeid);

	
	@Query("select f.id,f.classesname from TClasses f where f.grade.id = ?")
	public List<TClasses> findByclassesByGradeid(Long areaId);
	//@Query(value="select a from TClasses a where  a.gradeid  = ?1",nativeQuery=true)
	//List<TClasses> findByclassesByGradeid(Long gradeid);

	
	
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

	@Modifying
	@Query("delete from TClasses r where r.id = ?")
	void deleteByClasses(Long id);
	
}

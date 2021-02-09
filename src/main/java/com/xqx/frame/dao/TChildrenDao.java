package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;



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
	 * 根据学生姓名查询返回josn
	 * @param name
	 * @return
	 */

	@Query("select distinct f from TChildren f where f.childName = ?")
	public List<TChildren> findBychildrennamejson(String areaId);
	
	
	/**
	 * 根据学生id查询
	 * @param name
	 * @return
	 */
	@Query("from TChildren where id = ?1")
	//@Query(value = "select childName from TChildren f where f.id = ?' ", nativeQuery = true)
	public TChildren findBychildrenById(Long id);
	
	/**
	 * 根据学生姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TChildren where childName = ?1")
	List<TChildren> findBychildrenname(String name);

	/**
	 * 
	 * @param name
	 * @param pgjg
	 * @param email
	 * @param phone
	 * @return
	 */
	@Query("from TChildren where childName = ?1")
	List<TChildren> findByAll(String name);

	
    @Transactional
    @Modifying
    @Query("delete from TPayedInfo t where children.id = ?")
    void deleteByTChildrenId(long id);
    
    
	@Procedure(name="getSystemSeq")
	String getSystemSeq(@Param("name")String name);
}

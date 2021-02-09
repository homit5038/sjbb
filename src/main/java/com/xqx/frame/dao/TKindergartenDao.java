package com.xqx.frame.dao;

import java.util.List;


import com.xqx.frame.model.TEmploye;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.TKindergarten;


@Repository
public interface TKindergartenDao extends JpaRepository<TKindergarten, String>, JpaSpecificationExecutor<TKindergarten> {

/*@Repository
public interface TKindergartenDao extends JpaRepository<TKindergarten, Long> {*/
	/**
	 * 多条件查询
	 * @param spec
	 * @param pageable
	 * @return
	 */
	//Page<TKindergarten> findAll(Specification<TKindergarten> spec,Pageable page);

	/*@Query("from TKindergarten u JOIN TUser s on s.id = u.id where s.id=?1")
	public TKindergarten  findTuserById(Long id);*/
	Page<TKindergarten> findAll(Pageable page);




	/**
	 * 根据学生id查询
	 * @param name
	 * @return
	 */
	@Query("from TKindergarten where id = ?1")
	//@Query(value = "select childName from TChildren f where f.id = ?' ", nativeQuery = true)
	public TKindergarten findByKindergartenById(Long id);

	/**
	 * 根据学生姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TKindergarten where Kindergartenname = ?1")
	List<TKindergarten> findByKindergartename(String name);


	/**
	 * 根据学生姓名查询
	 */
	@Query("from TKindergarten where fisSubKingdergarten=0 and updateUserID= ?1")
	List<TKindergarten> findKindergartenByUserId(Long userId);


	/**
	 * 根据姓名、评估机构、电子邮箱、电话查找专家
	 * @param name
	 * @return
	 */
	@Query("from TKindergarten where Kindergartenname = ?1")
	List<TKindergarten> findByAll(String name);

	
}

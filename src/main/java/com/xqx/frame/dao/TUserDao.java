package com.xqx.frame.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TUser;

@Repository
public interface TUserDao extends JpaRepository<TUser, Long> {

	/**
	 * 用户名查找用户
	 * 
	 * @param loginName
	 * @return
	 */
	@Query("from TUser u where u.loginName = ?1 and u.availability <> 0 ")
	public TUser getUserByLoginName(String loginName);

	/**
	 * 多条件条件查询
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<TUser> findAll(Specification<TUser> spec, Pageable pageable);
}

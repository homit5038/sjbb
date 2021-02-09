package com.xqx.frame.dao;

import com.xqx.frame.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TUserDao extends JpaRepository<TUser, Long> {

	/**
	 * 用户名查找用户
	 * 
	 * @param name
	 * @return
	 */
	@Query("from TUser where loginName = ?1 and availability <> 0 ")
	public TUser getUserByLoginName(String name);

	/**
	 * 多条件条件查询
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<TUser> findAll(Specification<TUser> spec, Pageable pageable);
}

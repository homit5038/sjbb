package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TChargeItem;


@Repository

public interface TchargeItemDao extends JpaRepository<TChargeItem, Long>{
	
	Page<TChargeItem> findAll(Specification<TChargeItem> spec,Pageable page);
	
	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TChargeItem where name = ?1")
	List<TChargeItem> findByChargeItemname(String name);
	

}

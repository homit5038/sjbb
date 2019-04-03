package com.xqx.frame.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TExpertBatch;

/**
 * 批次管理DAO
 * 
 * @author
 *
 */
@Repository
public interface ExpertBatchDao extends JpaRepository<TExpertBatch, Long> {

	/**
	 * 多条件条件查询
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<TExpertBatch> findAll(Specification<TExpertBatch> spec, Pageable pageable);
}

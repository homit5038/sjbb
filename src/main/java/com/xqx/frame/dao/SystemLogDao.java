package com.xqx.frame.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xqx.frame.model.SystemLog;

public interface SystemLogDao extends JpaRepository<SystemLog, Long> {
	/**
	 * 查询所有日志
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<SystemLog> findAll(Specification<SystemLog> spec,Pageable pageable);
}

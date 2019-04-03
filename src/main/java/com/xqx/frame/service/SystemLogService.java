package com.xqx.frame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.model.SystemLog;

/**
 * @author yc
 * 
 * @date 2017-03-10
 * 
 * @Description 日志Service
 */

public interface SystemLogService {

	/**
	 * 保存日志
	 * 
	 * @param log
	 */
	public void saveLog(SystemLog log);
	
	public List<SystemLog> findAllLog();

	public Page<SystemLog> findAll(final String beginTime, final String endTime,
			final String userName,Pageable p );
}

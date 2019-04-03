package com.xqx.frame.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xqx.frame.dao.SystemLogDao;
import com.xqx.frame.model.SystemLog;

/**
 * @author yc
 * 
 * @date 2017-03-09
 * 
 * @Description 日志Impl
 */

@Service
public class SystemLogServiceImpl implements SystemLogService {

	@Autowired
	private SystemLogDao logDao;

	@Override
	@Transactional
	public void saveLog(SystemLog log) {
		logDao.save(log);
	}

	@Override
	public List<SystemLog> findAllLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SystemLog> findAll(final String beginTime, final String endTime,
			final String userName, Pageable p) {
		return logDao.findAll(new Specification<SystemLog>() {
			
			@Override
			public Predicate toPredicate(Root<SystemLog> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(!StringUtils.isEmpty(userName)){
					predicate.getExpressions().add(
							cb.equal(root.get("userName"), userName));
				}
				if (!StringUtils.isEmpty(beginTime)
						&& !StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.between(root.<Date> get("operTime"),
										sdf.parse(beginTime),
										sdf.parse(endTime)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (!StringUtils.isEmpty(beginTime)
						&& StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.between(root.<Date> get("operTime"),
										sdf.parse(beginTime), new Date()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (StringUtils.isEmpty(beginTime)
						&& !StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.lessThanOrEqualTo(root.<Date> get("operTime"),
										sdf.parse(endTime)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				query.where(predicate);
				return null;
			}
		}, p);
	}
	
	
}

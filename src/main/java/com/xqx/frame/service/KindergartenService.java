package com.xqx.frame.service;
import java.util.List;

import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.query.employeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TKindergarten;
import com.xqx.frame.model.dto.TKindergartenQueryObject;


public interface KindergartenService {
	public String saveKindergarten(TKindergarten kindergarten) throws ParameterCheckException;

	Page<TKindergarten> findAll(Pageable pageable);

	public TKindergarten findKindergartenById(long id);
	public List<TKindergarten> findKindergartenByName(String name);
	public Page<TKindergarten> findAll(Pageable pageable,final TKindergartenQueryObject queryObject);
	public void deleteTKindergarten(long id);
}

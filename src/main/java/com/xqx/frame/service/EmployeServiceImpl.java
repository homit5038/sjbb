package com.xqx.frame.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.xqx.frame.dao.TEmployeDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.PageResult;
import com.xqx.frame.model.SexType;
import com.xqx.frame.model.SystemLog;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.query.employeQuery;
import com.xqx.frame.security.SecurityUtil;
@Service
public class EmployeServiceImpl implements EmployeService {

	
	@Autowired
	TEmployeDao employeDao;
	@Autowired
	FileService fileservice;
	@Override
	public String saveEmploye(TEmploye employe) throws ParameterCheckException{
		if (employe != null) {
			// 新建的专家
			
			
		
			if (employe.getId() == null) {
				// 判断专家是否存在
				List<TEmploye> expExists = employeDao.findByAll(employe.getEmployeName(),
						employe.getIdcardNumber(), employe.getEmployeEmail(), 
						employe.getPhoneNum());
				if (expExists != null && expExists.size()>0) {
					return ("exist");
				}
				employeDao.save(employe);
			
			} else {
				// 修改已存在的专家
				//TEmploye EmpExist = employeDao.findOne(employe.getId());

				//employe.setClassName(EmpExist.getClassName());
				SexType.values();
				employeDao.save(employe);
			}
			
			
			
			return "ok";
		}else{
			return "error";
		}
	}

	@Override
	public TEmploye findEmployeById(long id) {
		return employeDao.findOne(id);
	}

	@Override
	public List<TEmploye> findEmployeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TEmploye> findAllt(employeQuery query, Integer page, Integer size) {
		return employeDao.findAll(query.getSpecification(), query.getPageable());
	}
	
	
	@Override
	public Page<TEmploye> findAll(final String name, Pageable pageable) {


		return employeDao.findAll(new Specification<TEmploye>() {
			@Override
			public Predicate toPredicate(Root<TEmploye> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                TUser user = SecurityUtil.getCurrentUser();
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("employeName"), name));  
                }  
              //  if (user != null) {
                   // predicates.add(cb.equal(root.get("id"),user.getId() ));  
               // }  
             
                
             
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
		
		
		
	}

	@Override
	public List<TEmploye> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TEmploye> findEmployeByAll(String name, String idcardNumber, String email, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmploye(long id) {
		TEmploye employe = employeDao.findOne(id);
        employeDao.delete(employe);

	}

	@Override
	public Map<String, Object> impData(MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

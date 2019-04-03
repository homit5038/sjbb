package com.xqx.frame.service;

import java.util.ArrayList;
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

import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.dao.TEmployeDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;
@Service
public class ChildrenServiceImpl implements ChildrenService {
	
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	FileService fileservice;
	@Override
	public String saveChildren(TChildren children) throws ParameterCheckException {

		if (children != null) {
			// 新建的专家
			
			
			childrenDao.save(children);
			
			
			
			return "ok";
		}else{
			return "error";
		}
		
	}

	@Override
	public TChildren findChildrenById(long id) {
		return childrenDao.findOne(id);
	}

	@Override
	public List<TChildren> findChildrenByName(String name) {
		
		if(!StringUtils.isEmpty(name)){  
		   return childrenDao.findBychildrenname(name);
		}else {
		   return childrenDao.findAll();
		}
	}


	
	@Override
	public Page<TChildren> findAll(final String name,final String cid,final String gid,Pageable pageable) {

		return childrenDao.findAll(new Specification<TChildren>() {
			@Override
			public Predicate toPredicate(Root<TChildren> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                TUser user = SecurityUtil.getCurrentUser();
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("childName"), name));  
                }
                
                if(!StringUtils.isEmpty(gid)){  
                    predicates.add(cb.equal(root.<TGrade>get("grade").<Long>get("id"), gid));  
                }  
                if(!StringUtils.isEmpty(cid)){  
                    predicates.add(cb.equal(root.<TClasses>get("classe").<Long>get("id"),cid));  
                
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
	public List<TChildren> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TChildren> findChildrenByAll(String name, String idcardNumber, String email, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteChildren(long id) {
		TChildren children = childrenDao.findOne(id);
		childrenDao.delete(children);

	}

	@Override
	public Map<String, Object> impData(MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

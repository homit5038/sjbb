package com.xqx.frame.service;

import java.nio.file.Path;
import java.util.ArrayList;
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
import org.springframework.util.StringUtils;

import com.xqx.frame.dao.TClasseDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
@Service
public class ClassesServiceImpl implements ClassesService {
	
	
	
	@Autowired
	TClasseDao classesDao;
	
	@Override
	public String saveClasses(TClasses classes) throws ParameterCheckException {
		if (classes != null) {
			classesDao.save(classes);
			return "ok";
		}else{
			return "error";
		}
	}

	@Override
	public TClasses findClassesById(long id) {
		return classesDao.findOne(id);
	}

	@Override
	public List<TClasses> findClassesByClassesName(String name) {
		return classesDao.findByclassesname(name);
	}

	@Override
	public Page<TClasses> findAll(final String name, Pageable pageable) {

		return classesDao.findAll(new Specification<TClasses>() {
			
			@Override
			public Predicate toPredicate(Root<TClasses> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("classesname"), name));  
                    //List<TGrade> membertypes = (List<TGrade>) root.get("childClassId");
                   // System.out.println(membertypes.size());
                }  
               
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}

	@Override
	public List<TClasses> findAll() {
		List<TClasses> classes = classesDao.findAll();
		return classes;
	}

	@Override
	public void deleteClasses(long id) {
		TClasses classes = classesDao.findOne(id);
		
		classesDao.delete(classes);

	}

}

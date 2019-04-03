package com.xqx.frame.service;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TGrade;
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	TGradeDao gradeDao;
	
	@Autowired
	private FileService fileService;
	
	@Override
	public String saveGrade(TGrade grade) throws ParameterCheckException, IOException {
		if (grade != null) {
			gradeDao.save(grade);
			return "ok";
		}else{
			return "error";
		}
	}
		
	@Override
	public String saveGradePhote(TGrade grade,MultipartFile photoDir) throws ParameterCheckException, IOException {
		
		if (grade != null) {
			
			fileService.upphote(photoDir);
			gradeDao.save(grade);
			return "ok";
		}else{
			return "error";
		}
	}
		
	
	
	@Override
	public TGrade findGradeById(long id) {
		return gradeDao.findOne(id);
	}

	@Override
	public List<TGrade> findGradeByGradeName(String name) {
		return gradeDao.findBygradename(name);
	}
	

	@Override
	public List<TGrade> findAll() {
		List<TGrade> grade = gradeDao.findAll();
		return grade;
	}
	
	
	
	
	
	@Override
	public Page<TGrade> findAll(final String name,Pageable pageable) {
		
		return gradeDao.findAll(new Specification<TGrade>() {
			
			@Override
			public Predicate toPredicate(Root<TGrade> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("gradename"), name));  
                }  
                
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}
	

	@Override
	public void deleteGrade(long id) {
		TGrade grade = gradeDao.findOne(id);
		
		gradeDao.delete(grade);

	}
	
	
	public void ichargeitem(long id,String ster) {
		TGrade grade = gradeDao.findOne(id);
		grade.setChargeitem(ster);
		gradeDao.save(grade);
		
	};
	

}
